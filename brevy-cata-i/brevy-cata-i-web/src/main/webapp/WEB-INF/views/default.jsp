<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>HOME PAGE</title>
	<%@ include file="/asserts/common/common.jsp"%>
	<pack:style src="/WEB-INF/views/default.css"/>
</head>
<body>			
	<div class="container">	
		<h1>Welcome to brevy's website</h1>
	
	    <h2>Mobile-Friendly API Documentation</h2>
	    <p class="lead">
	        Emulate tables that collapse beautifully on mobile devices!
	    </p>
	    
	    <div class="alert alert-info">
	        <h4>Row modifier class included</h4>
	        <p>This feature uses a row modifier I created called ".margin-0". When applied to a ".row" element, it will ensure the margins between the columns will be 0. </p>
	        <p>This is important in order to emulate the table-like effect, but it will work in any environment.</p>
	    </div>
	
	    <hr />
	
	    <div class="method">
	        <div class="row margin-0 list-header hidden-sm hidden-xs">
	            <div class="col-md-3"><div class="header">Property</div></div>
	            <div class="col-md-2"><div class="header">Type</div></div>
	            <div class="col-md-2"><div class="header">Required</div></div>
	            <div class="col-md-5"><div class="header">Description</div></div>
	        </div>
	
	        <div class="row margin-0">
	            <div class="col-md-3">
	                <div class="cell">
	                    <div class="propertyname">
	                        CurrencyCode  <span class="mobile-isrequired">[Required]</span>
	                    </div>
	                </div>
	            </div>
	            <div class="col-md-2">
	                <div class="cell">
	                    <div class="type">
	                        <code>String</code>
	                    </div>
	                </div>
	            </div>
	            <div class="col-md-2">
	                <div class="cell">
	                    <div class="isrequired">
	                        Yes
	                    </div>
	                </div>
	            </div>
	            <div class="col-md-5">
	                <div class="cell">
	                    <div class="description">
	                        The standard ISO 4217 3-letter currency code
	                    </div>
	                </div>
	            </div>
	        </div>
	        <div class="row margin-0">
	            <div class="col-md-3">
	                <div class="cell">
	                    <div class="propertyname">
	                        PriceType  <span class="mobile-isrequired">[Required]</span>
	                    </div>
	                </div>
	            </div>
	            <div class="col-md-2">
	                <div class="cell">
	                    <div class="type">
	                        <code>Int32</code>
	                    </div>
	                </div>
	            </div>
	            <div class="col-md-2">
	                <div class="cell">
	                    <div class="isrequired">
	                        Yes
	                    </div>
	                </div>
	            </div>
	            <div class="col-md-5">
	                <div class="cell">
	                    <div class="description">
	                        The type of price
	                    </div>
	                </div>
	            </div>
	        </div>
	        <div class="row margin-0">
	            <div class="col-md-3">
	                <div class="cell">
	                    <div class="propertyname">
	                        WarehouseID  <span class="mobile-isrequired">[Required]</span>
	                    </div>
	                </div>
	            </div>
	            <div class="col-md-2">
	                <div class="cell">
	                    <div class="type">
	                        <code>Int32</code>
	                    </div>
	                </div>
	            </div>
	            <div class="col-md-2">
	                <div class="cell">
	                    <div class="isrequired">
	                        Yes
	                    </div>
	                </div>
	            </div>
	            <div class="col-md-5">
	                <div class="cell">
	                    <div class="description">
	                        The unique identifier for the warehouse
	                    </div>
	                </div>
	            </div>
	        </div>
	        <div class="row margin-0">
	            <div class="col-md-3">
	                <div class="cell">
	                    <div class="propertyname">
	                        ItemCodes
	                    </div>
	                </div>
	            </div>
	            <div class="col-md-2">
	                <div class="cell">
	                    <div class="type">
	                        <code>String[]</code>
	                    </div>
	                </div>
	            </div>
	            <div class="col-md-2">
	                <div class="cell">
	                    <div class="isrequired">
	                        <span class="text-muted">No</span>
	                    </div>
	                </div>
	            </div>
	            <div class="col-md-5">
	                <div class="cell">
	                    <div class="description">
	
	                    </div>
	                </div>
	            </div>
	        </div>
	        <div class="row margin-0">
	            <div class="col-md-3">
	                <div class="cell">
	                    <div class="propertyname">
	                        LanguageID
	                        <a class="lookuplink" href="javascript:;">
	                            <i class="glyphicon glyphicon-search"></i>
	                        </a>
	                    </div>
	                </div>
	            </div>
	            <div class="col-md-2">
	                <div class="cell">
	                    <div class="type">
	                        <code>Int32?</code>
	                    </div>
	                </div>
	            </div>
	            <div class="col-md-2">
	                <div class="cell">
	                    <div class="isrequired">
	                        <span class="text-muted">No</span>
	                    </div>
	                </div>
	            </div>
	            <div class="col-md-5">
	                <div class="cell">
	                    <div class="description">
	                        The customer's preferred language ID (ex. 0 (English), 1 (Spanish), etc.)
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
	</div>
</body>
</html>