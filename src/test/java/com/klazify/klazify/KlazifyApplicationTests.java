package com.klazify.klazify;

import com.klazify.klazify.Entities.Categories;
import com.klazify.klazify.Entities.SocialMedias;
import com.klazify.klazify.Entities.Websites;
import com.klazify.klazify.Repozitories.CategoriesRepo;
import com.klazify.klazify.Repozitories.SocialMediaRepo;
import com.klazify.klazify.Repozitories.WebsitesRepo;
import com.klazify.klazify.Services.KlazifyService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.postgresql.hostchooser.HostRequirement.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class KlazifyApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Mock
	WebsitesRepo websitesRepo;

	@Mock
	CategoriesRepo categoriesRepo;

	@Mock
	SocialMediaRepo socialMediasRepo;

	@Mock
	KlazifyService klazifyService;

	@Test
	public void getWebsiteTest() throws Exception{
		Websites new_website = new Websites();
		new_website.setUrl("instagram.com");
		when(klazifyService.findSingleWebsite(new_website.getUrl())).thenReturn(new_website);

		Assert.assertEquals(new_website, klazifyService.findSingleWebsite(new_website.getUrl()));

		mvc.perform(get("/api/website/get/{url}", "google.com"))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void postAddWebsiteTest() throws Exception{

		Websites new_website = new Websites();
		new_website.setUrl("https://instagram.com");
		new_website.setLogoUrl("https://klazify.s3.amazonaws.com/21174115511611461829600cf4c558ee32.20602258.png");

		List<Categories> categoriesList = new ArrayList<>();
		Categories new_category = new Categories();
		Categories new_category2 = new Categories();
		Categories new_category3 = new Categories();
		new_category.setName("/Arts & Entertainment/Online Media/Online Image Galleries");
		new_category.setConfidence(0.89);
		new_category.setWebsites(new_website);

		new_category2.setName("/Online Communities/Photo & Video Sharing/Photo & Image Sharing");
		new_category2.setConfidence(0.88);
		new_category2.setWebsites(new_website);

		new_category3.setName("/Online Communities/Social Networks");
		new_category3.setConfidence(0.77);
		new_category3.setWebsites(new_website);

		categoriesList.add(new_category);

		new_website.setCategories(categoriesList);

		SocialMedias new_social = null;

		when(klazifyService.addWebsite(new_website.getUrl())).thenReturn(new_website);

		mvc.perform(post("/api/website/add/{url}", "instagram.com"))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
				.andExpect(jsonPath("$.url").value(new_website.getUrl()))
				.andExpect(jsonPath("$.socialMedia").value(new_website.getSocialMedia()))
				.andExpect(jsonPath("$.logoUrl").value(new_website.getLogoUrl()))
				.andExpect(jsonPath("$.categories.[0].name").value(new_category.getName()))
				.andExpect(jsonPath("$.categories.[0].confidence").value(new_category.getConfidence()))
				.andExpect(jsonPath("$.categories.[1].name").value(new_category2.getName()))
				.andExpect(jsonPath("$.categories.[1].confidence").value(new_category2.getConfidence()))
				.andExpect(jsonPath("$.categories.[2].name").value(new_category3.getName()))
				.andExpect(jsonPath("$.categories.[2].confidence").value(new_category3.getConfidence()))
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void getWebsiteCategoryTest() throws Exception {
		Categories new_category = new Categories();
		long id = 21;
		new_category.setId(id);
		new_category.setName("/Arts & Entertainment/Online Media/Online Image Galleries");
		new_category.setConfidence(0.89);

		when(klazifyService.getSingleWebsiteCategory(new_category.getId())).thenReturn(new_category);

		Assert.assertEquals(new_category, klazifyService.getSingleWebsiteCategory(new_category.getId()));

		mvc.perform(get("/api/website/get/category/{id}", id))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
				.andExpect(jsonPath("$.id").value(new_category.getId()))
				.andExpect(jsonPath("$.name").value(new_category.getName()))
				.andExpect(jsonPath("$.confidence").value(new_category.getConfidence()))
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void deleteWebsiteTest() throws Exception{
		Websites new_website = Mockito.spy(new Websites());
		new_website.setUrl("instagram.com");

		when(klazifyService.deleteWebsite(new_website.getUrl())).thenReturn(true);

		Assert.assertEquals(true, klazifyService.deleteWebsite(new_website.getUrl()));

	}

	@Test
	public void patchWebsiteCategory() throws Exception{
		Categories new_category = new Categories();
		long id = 21;
		new_category.setId(id);
		new_category.setName("New category name test");
		new_category.setConfidence(0.89);

		when(klazifyService.updateWebsiteCategory(new_category.getName(), new_category.getId())).thenReturn(new ResponseEntity<Categories>(new_category, HttpStatus.OK));

		Assert.assertEquals(new ResponseEntity<Categories>(new_category, HttpStatus.OK), klazifyService.updateWebsiteCategory(new_category.getName(), new_category.getId()));

		mvc.perform(patch("/api/website/patch/category/{category_id}/{new_category_name}", id, new_category.getName()))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
				.andExpect(jsonPath("$.id").value(new_category.getId()))
				.andExpect(jsonPath("$.name").value(new_category.getName()))
				.andExpect(jsonPath("$.confidence").value(new_category.getConfidence()))
				.andDo(MockMvcResultHandlers.print());

	}
}
