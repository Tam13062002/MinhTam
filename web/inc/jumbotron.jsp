<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="bg-primary text-white py-5">
            <div class="container py-5">
                <h1>
                    <c:if test="${id_category==null}" >
                    Best Categories & <br/>
                    Product in our store
                    </c:if>
                     <c:if test="${id_category!=null}" >
                         <c:forEach items="${listCategory}" var="category">
                             <c:if test="${id_category==category.id}" >
                          Best ${category.name} & <br/>
                    Product in our store   
                              </c:if>
                         </c:forEach>
                        </c:if>
                </h1>
                <p>
                    Trendy Products, Factory Prices, Excellent Service
                </p>
                <button type="button" class="btn btn-outline-light">
                    Learn more
                </button>
                <button type="button" class="btn btn-light shadow-0 text-primary pt-2 border border-white">
                    <span class="pt-1">Purchase now</span>
                </button>
            </div>
        </div>