package com.brevy.plugin.remove;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import com.brevy.util.MatcherUtils;

/**
 *
 * 文件移除GOAL#caobin#2013-7-18
 * 
 * @goal remove
 */
public class RemoveMojo extends AbstractMojo {

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {	
		Validate.notNull(goalRemove.getResources(), "resources is required");
		List<File> collected = new ArrayList<File>();
		for(Resource resource : goalRemove.getResources()){	
			getLog().info(String.format(">>>>> Base Dir: %s", resource.getBaseDirectory()));
			for(String filePattern : resource.getFilePatterns()){
				getLog().info(String.format(">>>>> FilePattern: %s", filePattern));	
				MatcherUtils.collectFiles(collected, resource.getBaseDirectory(), filePattern, true);
				getLog().info(String.format(">>>>> Collected: %s", collected));
				for(File f : collected){
					if(f.canWrite()){
						boolean deleted = f.delete();
						if(deleted){
							this.getLog().info(String.format("deleting file %s", f.getAbsolutePath()));
						}	
					}		
				}
				collected.clear();
			}
		}
	}

	
	/**
	 * goal为remove的参数集
	 * @parameter
	 * @required
	 */
	private GoalRemove goalRemove;
}
