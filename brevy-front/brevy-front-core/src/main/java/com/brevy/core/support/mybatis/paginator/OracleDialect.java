package com.brevy.core.support.mybatis.paginator;

/**
 * @Description 修正左右连接时，由于rownum造成的分页问题
 * @author caobin
 * @date 2014-5-26
 * @version 1.0
 */
public class OracleDialect extends
		com.github.miemiedev.mybatis.paginator.dialect.OracleDialect {

	public String getLimitString(String sql, int offset,String offsetPlaceholder, int limit, String limitPlaceholder) {
		sql = sql.trim();
		boolean isForUpdate = false;
		if ( sql.toLowerCase().endsWith(" for update") ) {
			sql = sql.substring( 0, sql.length()-11 );
			isForUpdate = true;
		}
		
		StringBuffer pagingSelect = new StringBuffer( sql.length()+100 );
		//modify by caobin
		pagingSelect.append("select * from ( select row_.*, rownum rownum_ from ( ");
		
		pagingSelect.append(sql);

//		int end = offset+limit;
		String endString = offsetPlaceholder+"+"+limitPlaceholder;
		pagingSelect.append(" ) row_ ) where rownum_ <= " + endString + " and rownum_ > " + offsetPlaceholder);
		

		if ( isForUpdate ) {
			pagingSelect.append( " for update" );
		}
		
		return pagingSelect.toString();
	}
}
