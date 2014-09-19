package com.brevy.commons.vfs.provider.sftp;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.Collection;

import org.apache.commons.vfs2.Capability;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemOptions;
import org.apache.commons.vfs2.UserAuthenticationData;
import org.apache.commons.vfs2.provider.AbstractFileName;
import org.apache.commons.vfs2.provider.GenericFileName;
import org.apache.commons.vfs2.provider.sftp.SftpClientFactory;
import org.apache.commons.vfs2.provider.sftp.SftpFileObject;
import org.apache.commons.vfs2.provider.sftp.SftpFileProvider;
import org.apache.commons.vfs2.provider.sftp.SftpFileSystem;
import org.apache.commons.vfs2.provider.sftp.SftpFileSystemConfigBuilder;
import org.apache.commons.vfs2.util.UserAuthenticatorUtils;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

/**
 * @Description 自定义SftpFileSystem(编码扩展)
 * @author caobin
 * @date 2014-5-20
 * @version 1.0
 */
public class CustomSftpFileSystem extends SftpFileSystem {

	private Session session;
	// private final JSch jSch;
	private ChannelSftp idleChannel;

	protected CustomSftpFileSystem(final GenericFileName rootName,
			final Session session, final FileSystemOptions fileSystemOptions) {
		super(rootName, null, fileSystemOptions);
		this.session = session;
	}

	@Override
	protected void doCloseCommunicationLink() {
		if (idleChannel != null) {
			idleChannel.disconnect();
			idleChannel = null;
		}

		if (session != null) {
			session.disconnect();
			session = null;
		}
	}

	/**
	 * Returns an SFTP channel to the server.
	 */
	protected ChannelSftp getChannel() throws IOException {
		if (this.session == null || !this.session.isConnected()) {
			doCloseCommunicationLink();

			// channel closed. e.g. by freeUnusedResources, but now we need it
			// again
			Session session;
			UserAuthenticationData authData = null;
			try {
				final GenericFileName rootName = (GenericFileName) getRootName();

				authData = UserAuthenticatorUtils.authenticate(
						getFileSystemOptions(),
						SftpFileProvider.AUTHENTICATOR_TYPES);

				session = SftpClientFactory.createConnection(rootName
						.getHostName(), rootName.getPort(),
						UserAuthenticatorUtils.getData(authData,
								UserAuthenticationData.USERNAME,
								UserAuthenticatorUtils.toChar(rootName
										.getUserName())),
						UserAuthenticatorUtils.getData(authData,
								UserAuthenticationData.PASSWORD,
								UserAuthenticatorUtils.toChar(rootName
										.getPassword())),
						getFileSystemOptions());
			} catch (final Exception e) {
				throw new FileSystemException(
						"vfs.provider.sftp/connect.error", getRootName(), e);
			} finally {
				UserAuthenticatorUtils.cleanup(authData);
			}

			this.session = session;
		}

		try {
			// Use the pooled channel, or create a new one
			final ChannelSftp channel;
			if (idleChannel != null) {
				channel = idleChannel;
				idleChannel = null;
			} else {
				channel = (ChannelSftp) session.openChannel("sftp");
	
				channel.connect();
				
				//add by caobin
				channel.setFilenameEncoding("ISO8859-1");

				Boolean userDirIsRoot = SftpFileSystemConfigBuilder
						.getInstance().getUserDirIsRoot(getFileSystemOptions());
				String workingDirectory = getRootName().getPath();
				if (workingDirectory != null
						&& (userDirIsRoot == null || !userDirIsRoot
								.booleanValue())) {
					try {
						channel.cd(workingDirectory);
					} catch (SftpException e) {
						throw new FileSystemException(
								"vfs.provider.sftp/change-work-directory.error",
								workingDirectory);
					}
				}
			}

			return channel;
		} catch (final Exception e) {
			throw new FileSystemException("vfs.provider.sftp/connect.error",
					getRootName(), e);
		}
	}

	/**
	 * Returns a channel to the pool.
	 */
	protected void putChannel(final ChannelSftp channel) {
		if (idleChannel == null) {
			// put back the channel only if it is still connected
			if (channel.isConnected() && !channel.isClosed()) {
				idleChannel = channel;
			}
		} else {
			channel.disconnect();
		}
	}

	/**
	 * Adds the capabilities of this file system.
	 */
	@Override
	protected void addCapabilities(final Collection<Capability> caps) {
		caps.addAll(CustomSftpFileProvider.capabilities);
	}

	/**
	 * Creates a file object. This method is called only if the requested file
	 * is not cached.
	 */
	@Override
	protected FileObject createFile(final AbstractFileName name)
			throws FileSystemException {
		try {
			Constructor<SftpFileObject> constructor = SftpFileObject.class.getDeclaredConstructor(AbstractFileName.class, SftpFileSystem.class);
			constructor.setAccessible(true);
			return (FileObject)constructor.newInstance(name, this);
		} catch (Exception e) {
			throw new FileSystemException(e);
		} 
	}

	/**
	 * last mod time is only a int and in seconds, thus can be off by 999.
	 * 
	 * @return 1000
	 */
	@Override
	public double getLastModTimeAccuracy() {
		return 1000L;
	}

}
