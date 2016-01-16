package cz.jiripinkas.jba.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cz.jiripinkas.jba.exception.PageNotFoundException;
import cz.jiripinkas.jba.service.BlogService;
import cz.jiripinkas.jba.service.ConfigurationService;

@Controller
@RequestMapping(value = "/spring/icon", produces = MediaType.IMAGE_PNG_VALUE)
public class IconController {
	
	private static final Logger log = LoggerFactory.getLogger(IconController.class);

	@Autowired
	private BlogService blogService;

	@Autowired
	private ConfigurationService configurationService;

	@ExceptionHandler(PageNotFoundException.class)
	public void pageNotFound(HttpServletResponse response) throws IOException {
		response.sendError(HttpServletResponse.SC_NOT_FOUND);
	}

	@RequestMapping
	public @ResponseBody byte[] getIcon() throws IOException {
		return configurationService.find().getIcon();
	}

	@RequestMapping(value = "/{blogId}")
	public @ResponseBody byte[] getBlogIcon(@PathVariable int blogId) throws IOException {
		try {
			return blogService.getIcon(blogId);
		} catch (PageNotFoundException e) {
			log.error("Icon not found! Blog id: {}", blogId);
			throw e;
		}
	}

	@RequestMapping(value = "/favicon")
	public @ResponseBody byte[] getFavicon() throws IOException {
		return configurationService.find().getFavicon();
	}

	@RequestMapping(value = "/appleTouchIcon")
	public @ResponseBody byte[] getAppleTouchIcon() throws IOException {
		return configurationService.find().getAppleTouchIcon();
	}

}
