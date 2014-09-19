package com.brevy.commons.vfs.provider.sftp;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.apache.commons.vfs2.Capability;
import org.apache.commons.vfs2.FileName;
import org.apache.commons.vfs2.FileSystem;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemOptions;
import org.apache.commons.vfs2.UserAuthenticationData;
import org.apache.commons.vfs2.provider.GenericFileName;
import org.apache.commons.vfs2.provider.sftp.SftpClientFactory;
import org.apache.commons.vfs2.provider.sftp.SftpFileProvider;
import org.apache.commons.vfs2.util.UserAuthenticatorUtils;

import com.jcraft.jsch.Session;

/**
 * @Description 自定义SftpFileProvider
 * @author caobin
 * @date 2014-5-20
 * @version 1.0
 */
public class CustomSftpFileProvider extends SftpFileProvider {

	 /** The provider's capabilities. */
    protected static final Collection<Capability> capabilities =
        Collections.unmodifiableCollection(Arrays.asList(new Capability[]
    {
        Capability.CREATE,
        Capability.DELETE,
        Capability.RENAME,
        Capability.GET_TYPE,
        Capability.LIST_CHILDREN,
        Capability.READ_CONTENT,
        Capability.URI,
        Capability.WRITE_CONTENT,
        Capability.GET_LAST_MODIFIED,
        Capability.SET_LAST_MODIFIED_FILE,
        Capability.RANDOM_ACCESS_READ
    }));
	
	/* (non-Javadoc)
	 * @see org.apache.commons.vfs2.provider.sftp.SftpFileProvider#doCreateFileSystem(org.apache.commons.vfs2.FileName, org.apache.commons.vfs2.FileSystemOptions)
	 */
	@Override
	protected FileSystem doCreateFileSystem(FileName name,
			FileSystemOptions fileSystemOptions) throws FileSystemException {
		final GenericFileName rootName = (GenericFileName) name;

        Session session;
        UserAuthenticationData authData = null;
        try
        {
            authData = UserAuthenticatorUtils.authenticate(fileSystemOptions, AUTHENTICATOR_TYPES);

            session = SftpClientFactory.createConnection(
                rootName.getHostName(),
                rootName.getPort(),
                UserAuthenticatorUtils.getData(authData, UserAuthenticationData.USERNAME,
                    UserAuthenticatorUtils.toChar(rootName.getUserName())),
                UserAuthenticatorUtils.getData(authData, UserAuthenticationData.PASSWORD,
                    UserAuthenticatorUtils.toChar(rootName.getPassword())),
                fileSystemOptions);
        }
        catch (final Exception e)
        {
            throw new FileSystemException("vfs.provider.sftp/connect.error",
                name,
                e);
        }
        finally
        {
            UserAuthenticatorUtils.cleanup(authData);
        }

        return new CustomSftpFileSystem(rootName, session, fileSystemOptions);
	}	
}
