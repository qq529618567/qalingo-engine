/**
 * Most of the code in the Qalingo project is copyrighted Hoteia and licensed
 * under the Apache License Version 2.0 (release version 0.8.0)
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *                   Copyright (c) Hoteia, 2012-2014
 * http://www.hoteia.com - http://twitter.com/hoteia - contact@hoteia.com
 *
 */
package org.hoteia.qalingo.web.mvc.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.hoteia.qalingo.core.domain.Localization;
import org.hoteia.qalingo.core.domain.enumtype.BoUrls;
import org.hoteia.qalingo.core.i18n.enumtype.ScopeWebMessage;
import org.hoteia.qalingo.core.web.mvc.factory.BackofficeViewBeanFactory;
import org.hoteia.qalingo.core.web.mvc.viewbean.MenuViewBean;
import org.hoteia.qalingo.core.web.resolver.RequestData;

/**
 * 
 */
public class BoBusinessViewBeanFactory extends BackofficeViewBeanFactory {

	/**
	 * @throws Exception 
	 * 
	 */
	@Override
	public List<MenuViewBean> buildListViewBeanHeaderMenu(final RequestData requestData) throws Exception {
		final HttpServletRequest request = requestData.getRequest();
		final Localization localization = requestData.getMarketAreaLocalization();
		final Locale locale = localization.getLocale();
		final String currentUrl = requestUtil.getCurrentRequestUrl(request);
		
		final List<MenuViewBean> menuViewBeans = new ArrayList<MenuViewBean>();
		
		MenuViewBean menu = new MenuViewBean();
		menu.setActive(currentUrl.contains(BoUrls.HOME.getUrlPatternKey()));
		menu.setCssIcon("fa fa-home");
		menu.setName(getSpecificMessage(ScopeWebMessage.HEADER_MENU, "home", locale));
		menu.setUrl(backofficeUrlService.generateUrl(BoUrls.HOME, requestData));
		menuViewBeans.add(menu);

        List<String> catalogManagementUrls = new ArrayList<String>();
        catalogManagementUrls.add(BoUrls.MASTER_CATALOG.getUrlPatternKey());
        catalogManagementUrls.add(BoUrls.VIRTUAL_CATALOG.getUrlPatternKey());
        catalogManagementUrls.add(BoUrls.MASTER_CATEGORY_DETAILS.getUrlPatternKey());
        catalogManagementUrls.add(BoUrls.MASTER_CATEGORY_ADD.getUrlPatternKey());
        catalogManagementUrls.add(BoUrls.MASTER_CATEGORY_EDIT.getUrlPatternKey());
        catalogManagementUrls.add(BoUrls.VIRTUAL_CATEGORY_DETAILS.getUrlPatternKey());
        catalogManagementUrls.add(BoUrls.VIRTUAL_CATEGORY_ADD.getUrlPatternKey());
        catalogManagementUrls.add(BoUrls.VIRTUAL_CATEGORY_EDIT.getUrlPatternKey());
        catalogManagementUrls.add(BoUrls.PRODUCT_MARKETING_DETAILS.getUrlPatternKey());
        catalogManagementUrls.add(BoUrls.PRODUCT_MARKETING_ADD.getUrlPatternKey());
        catalogManagementUrls.add(BoUrls.PRODUCT_MARKETING_EDIT.getUrlPatternKey());
        catalogManagementUrls.add(BoUrls.PRODUCT_SKU_DETAILS.getUrlPatternKey());
        catalogManagementUrls.add(BoUrls.PRODUCT_SKU_ADD.getUrlPatternKey());
        catalogManagementUrls.add(BoUrls.PRODUCT_SKU_EDIT.getUrlPatternKey());
	        
		menu = new MenuViewBean();
        menu.setActive(menuIsActive(currentUrl, catalogManagementUrls));
        menu.setCssClass("dropdown");
		menu.setCssIcon("fa fa-sitemap");
		menu.setName("Catalog");
		menuViewBeans.add(menu);
		
		MenuViewBean subMenu = new MenuViewBean();
		subMenu.setActive(currentUrl.contains(BoUrls.MASTER_CATALOG.getUrlPatternKey()));
		subMenu.setName("Manage Master Catalog");
		subMenu.setUrl(backofficeUrlService.generateUrl(BoUrls.MASTER_CATALOG, requestData));
		menu.getSubMenus().add(subMenu);
		
		subMenu = new MenuViewBean();
		subMenu.setActive(currentUrl.contains(BoUrls.VIRTUAL_CATALOG.getUrlPatternKey()));
		subMenu.setName("Manage Virtual Catalog");
		subMenu.setUrl(backofficeUrlService.generateUrl(BoUrls.VIRTUAL_CATALOG, requestData));
		menu.getSubMenus().add(subMenu);
		
		List<String> wareHouseUrls = new ArrayList<String>();
		wareHouseUrls.add(BoUrls.WAREHOUSE_LIST.getUrlPatternKey());
        wareHouseUrls.add(BoUrls.WAREHOUSE_DETAILS.getUrlPatternKey());
        wareHouseUrls.add(BoUrls.WAREHOUSE_EDIT.getUrlPatternKey());
		
        menu = new MenuViewBean();
        menu.setActive(menuIsActive(currentUrl, wareHouseUrls));
        menu.setCssIcon("fa fa-building-o");
        menu.setName("Warehouse");
        menu.setUrl(backofficeUrlService.generateUrl(BoUrls.WAREHOUSE_LIST, requestData));
        menuViewBeans.add(menu);
        
        List<String> priceUrls = new ArrayList<String>();
        priceUrls.add(BoUrls.TAX_LIST.getUrlPatternKey());
        priceUrls.add(BoUrls.TAX_DETAILS.getUrlPatternKey());
        priceUrls.add(BoUrls.TAX_EDIT.getUrlPatternKey());
        
        menu = new MenuViewBean();
        menu.setActive(menuIsActive(currentUrl, priceUrls));
        menu.setCssIcon("fa fa-usd");
        menu.setName("Tax");
        menu.setUrl(backofficeUrlService.generateUrl(BoUrls.TAX_LIST, requestData));
        menuViewBeans.add(menu);
        
        List<String> ruleUrls = new ArrayList<String>();
        ruleUrls.add(BoUrls.RULE_LIST.getUrlPatternKey());
        
		menu = new MenuViewBean();
        menu.setActive(menuIsActive(currentUrl, ruleUrls));
		menu.setCssIcon("fa fa-money");
		menu.setName("Promotion");
		menu.setUrl(backofficeUrlService.generateUrl(BoUrls.RULE_LIST, requestData));
		menuViewBeans.add(menu);

        List<String> deliveryMethodUrls = new ArrayList<String>();
        deliveryMethodUrls.add(BoUrls.DELIVERY_METHOD_LIST.getUrlPatternKey());
        deliveryMethodUrls.add(BoUrls.DELIVERY_METHOD_DETAILS.getUrlPatternKey());
        deliveryMethodUrls.add(BoUrls.DELIVERY_METHOD_EDIT.getUrlPatternKey());
        
		menu = new MenuViewBean();
        menu.setActive(menuIsActive(currentUrl, deliveryMethodUrls));
		menu.setCssIcon("fa fa-truck");
		menu.setName("Delivery Method");
		menu.setUrl(backofficeUrlService.generateUrl(BoUrls.DELIVERY_METHOD_LIST, requestData));
		menuViewBeans.add(menu);
		
        List<String> orderUrls = new ArrayList<String>();
        orderUrls.add(BoUrls.ORDER_LIST.getUrlPatternKey());
        orderUrls.add(BoUrls.ORDER_DETAILS.getUrlPatternKey());
        
		menu = new MenuViewBean();
        menu.setActive(menuIsActive(currentUrl, orderUrls));
		menu.setCssIcon("fa fa-shopping-cart");
		menu.setName("Order");
		menu.setUrl(backofficeUrlService.generateUrl(BoUrls.ORDER_LIST, requestData));
		menuViewBeans.add(menu);
		
        List<String> customerUrls = new ArrayList<String>();
        customerUrls.add(BoUrls.CUSTOMER_LIST.getUrlPatternKey());
        customerUrls.add(BoUrls.CUSTOMER_DETAILS.getUrlPatternKey());
        customerUrls.add(BoUrls.CUSTOMER_EDIT.getUrlPatternKey());
        
		menu = new MenuViewBean();
        menu.setActive(menuIsActive(currentUrl, customerUrls));
		menu.setCssIcon("fa fa-group");
		menu.setName("Customer");
		menu.setUrl(backofficeUrlService.generateUrl(BoUrls.CUSTOMER_LIST, requestData));
		menuViewBeans.add(menu);
		
        List<String> retailerUrls = new ArrayList<String>();
        retailerUrls.add(BoUrls.RETAILER_LIST.getUrlPatternKey());
        retailerUrls.add(BoUrls.RETAILER_DETAILS.getUrlPatternKey());
        retailerUrls.add(BoUrls.RETAILER_EDIT.getUrlPatternKey());
        retailerUrls.add(BoUrls.STORE_LIST.getUrlPatternKey());
        retailerUrls.add(BoUrls.STORE_DETAILS.getUrlPatternKey());
        retailerUrls.add(BoUrls.STORE_EDIT.getUrlPatternKey());
        
		menu = new MenuViewBean();
        menu.setActive(menuIsActive(currentUrl, retailerUrls));
        menu.setCssIcon(" fa fa-map-marker");
		menu.setName("Retailer");
		menu.setUrl(backofficeUrlService.generateUrl(BoUrls.RETAILER_LIST, requestData));
        menuViewBeans.add(menu);
		
		return menuViewBeans;
	}
	
}