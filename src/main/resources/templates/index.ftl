<#-- @ftlvariable name="articles" type="kotlin.collections.List<com.example.models.Article>" -->
<#import "_layout.ftl" as layout />
<@layout.header>
    <#list customers?reverse as customer>
        <div>
            <h3>
                <p>${customer.firstName} ${customer.lastName}</p>
            </h3>
        </div>
    </#list>
    <hr>
    <p>
       <!-- <a href="/articles/new">Create article</a>-->
    </p>
</@layout.header>