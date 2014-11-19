/**
 * Most of the code in the Qalingo project is copyrighted Hoteia and licensed
 * under the Apache License Version 2.0 (release version 0.8.0)
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *                   Copyright (c) Hoteia, 2012-2014
 * http://www.hoteia.com - http://twitter.com/hoteia - contact@hoteia.com
 *
 */
package org.hoteia.qalingo.web.mvc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hoteia.qalingo.core.Constants;
import org.hoteia.qalingo.core.ModelConstants;
import org.hoteia.qalingo.core.fetchplan.FetchPlan;
import org.hoteia.qalingo.core.pojo.RequestData;
import org.hoteia.qalingo.core.service.WebManagementService;
import org.hoteia.qalingo.core.solr.response.ProductMarketingResponseBean;
import org.hoteia.qalingo.core.web.mvc.controller.AbstractFrontofficeQalingoController;
import org.hoteia.qalingo.core.web.mvc.viewbean.RecentProductViewBean;
import org.hoteia.qalingo.core.web.mvc.viewbean.SearchProductItemViewBean;
import org.hoteia.qalingo.web.mvc.factory.FormFactory;
import org.hoteia.qalingo.web.mvc.form.SearchForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.ui.Model;

/**
 * 
 * <p>
 * <a href="AbstractMCommerceController.java.html"><i>View Source</i></a>
 * </p>
 * 
 * @author Denis Gosset <a href="http://www.hoteia.com"><i>Hoteia.com</i></a>
 * 
 */
public abstract class AbstractMCommerceController extends AbstractFrontofficeQalingoController {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
    protected WebManagementService webManagementService;
	
	@Autowired
    protected FormFactory formFactory;
	
    protected void loadRecentProducts(final HttpServletRequest request, final RequestData requestData, final Model model, FetchPlan categoryVirtualFetchPlans, FetchPlan productMarketingFetchPlans, FetchPlan productSkuFetchPlans){
        try {
            final List<String> listProductSkuCodes = requestUtil.getRecentProductSkuCodesFromCookie(request);
            List<RecentProductViewBean> recentProductViewBeans = frontofficeViewBeanFactory.buildListViewBeanRecentProduct(requestData, listProductSkuCodes, categoryVirtualFetchPlans, productMarketingFetchPlans, productSkuFetchPlans);
            model.addAttribute(ModelConstants.RECENT_PPRODUCT_MARKETING_VIEW_BEAN, recentProductViewBeans);
            
        } catch (Exception e) {
            logger.error("Can't load recent products", e);
        }
    }
    
    protected PagedListHolder<SearchProductItemViewBean> initList(final HttpServletRequest request, final String sessionKeyPagedListHolder, final ProductMarketingResponseBean productMarketingResponseBean,
            PagedListHolder<SearchProductItemViewBean> productsViewBeanPagedListHolder, final SearchForm searchForm) throws Exception{
        int pageSize = searchForm.getPageSize();
        String sortBy = searchForm.getSortBy();
        String order = searchForm.getOrder();
        List<SearchProductItemViewBean> searchtItems = frontofficeViewBeanFactory.buildListViewBeanSearchProductItem(requestUtil.getRequestData(request), productMarketingResponseBean);
        productsViewBeanPagedListHolder = new PagedListHolder<SearchProductItemViewBean>(searchtItems);
        productsViewBeanPagedListHolder.setPageSize(pageSize);
        productsViewBeanPagedListHolder.setSort(new MutableSortDefinition(sortBy, true, Constants.PAGE_ORDER_ASC.equalsIgnoreCase(order)));
        productsViewBeanPagedListHolder.resort();
//        request.getSession().setAttribute(sessionKeyPagedListHolder, productsViewBeanPagedListHolder);
        return productsViewBeanPagedListHolder;
    }
}