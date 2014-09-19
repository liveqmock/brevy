package com.brevy.archetype.support.mybatis.paginator;

import org.apache.ibatis.mapping.MappedStatement;

import com.github.miemiedev.mybatis.paginator.dialect.OracleDialect;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

/**
 * @Description Oracle Dialect （修复左/右连接查询翻页顺序问题）
 * @author caobin
 * @date 2014-8-19
 * @version 1.0
 */
public class FixedOracleDialect extends OracleDialect {

	public FixedOracleDialect(MappedStatement mappedStatement,
			Object parameterObject, PageBounds pageBounds) {
		super(mappedStatement, parameterObject, pageBounds);
	}
	
	@Override
	protected String getLimitString(String sql, String offsetName,int offset, String limitName, int limit) {
		sql = sql.trim();
		boolean isForUpdate = false;
		if ( sql.toLowerCase().endsWith(" for update") ) {
			sql = sql.substring( 0, sql.length()-11 );
			isForUpdate = true;
		}
		
		StringBuffer pagingSelect = new StringBuffer(sql.length() + 100);
		pagingSelect
			.append("select * from ( select row_.*, rownum rownum_ from ( ")
			.append(sql)
			.append(" ) row_ ) where rownum_ <= ? and rownum_ > ?");
        setPageParameter("__offsetEnd", offset + limit, Integer.class);
        setPageParameter(offsetName, offset, Integer.class);
		
		if ( isForUpdate ) {
			pagingSelect.append( " for update" );
		}
		
		return pagingSelect.toString();
	}

}
