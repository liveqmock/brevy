package com.brevy.core.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.brevy.core.shiro.model.ApMenu;

/**
 * @Description 菜单工具
 * @author caobin
 * @date 2013-11-18
 * @version 1.0
 */
public class ApMenuUtils {

	
	/**
	 * @Description 静态菜单子节点生成
	 * @param list 当前List
	 * @param rootid root节点ID
	 * @param nestedList 内部List
	 * @return
	 * @author caobin
	 */
	public static List<ApMenu> generateSubApMenu(List<ApMenu> list, long rootid, List<ApMenu> nestedList) {
		ApMenu pNode = new ApMenu();
		pNode.setId(rootid);
		return generateSubApMenu(list, pNode, nestedList);
	}
	
	/**
	 * @Description 静态菜单子节点生成
	 * @param list  当前List
	 * @param pNode 节点
	 * @param nestedList 内部List
	 * @return
	 * @author caobin
	 */
	public static List<ApMenu> generateSubApMenu(List<ApMenu> list, ApMenu pNode, List<ApMenu> nestedList) {
		if(nestedList == null)nestedList = new ArrayList<ApMenu>();
		List<ApMenu> clist = searchSubList(list, pNode.getId());
		
		if (clist.size() > 0) {// 不是叶子节点		
			for(ApMenu ApMenu : clist){
				ApMenu result = new ApMenu();
				BeanUtils.copyProperties(ApMenu, result);
				if(result.getLeaf().equals("1")){
					result.setApMenus(generateSubApMenu(list, ApMenu, nestedList));
				}else{
					result.setApMenus(generateSubApMenu(list, ApMenu, new ArrayList<ApMenu>()));
					nestedList.add(result);
				}
				
			}
		} else {// 是叶子节点
			ApMenu result = new ApMenu();
			BeanUtils.copyProperties(pNode, result);
			result.setLeaf("1");
			nestedList.add(result);
		}
		return nestedList;
	}
	
	/**
	 * @Description 检索子集合
	 * @param list
	 * @param parentId
	 * @return
	 * @author caobin
	 */
	private static List<ApMenu> searchSubList(List<ApMenu> list, long parentId) {
		List<ApMenu> clist = new ArrayList<ApMenu>();
		for (ApMenu m : list) {
			ApMenu n = m;
			if (n.getParentId() == parentId) {
				clist.add(n);
			}
		}
		return clist;
	}
}
