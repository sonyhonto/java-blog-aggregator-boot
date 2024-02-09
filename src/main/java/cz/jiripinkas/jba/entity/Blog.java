package cz.jiripinkas.jba.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

import cz.jiripinkas.jba.annotation.UniqueBlog;
import cz.jiripinkas.jba.annotation.UniqueShortName;
import cz.jiripinkas.jba.util.MyUtil;

@Entity
public class Blog {

	@Id
	@GeneratedValue
	private Integer id;

	@Lob
	@Column(length = Integer.MAX_VALUE, updatable = false)
	private byte[] icon;

	@Size(min = 1, max = 1000, message = "Invalid URL!")
	@UniqueBlog(message = "This blog already exists!")
	@URL(message = "Invalid URL!")
	@Column(length = 1000, unique = true)
	private String url;

	@Size(min = 1, message = "Name must be at least 1 character!")
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(mappedBy = "blog", cascade = CascadeType.REMOVE)
	private List<Item> items;

	@Size(min = 1, max = 255, message = "Short name must be between 1 and 255 characters!")
	@UniqueShortName(message = "This short name already exists!")
	@NotNull
	@Column(name = "short_name", unique = true)
	private String shortName;

	private String nick;

	@NotNull
	@Size(min = 1, message = "Homepage cannot be empty!")
	@URL(message = "Invalid URL!")
	@Column(name = "homepage")
	private String homepageUrl;

	@Column(name = "last_check_status", updatable = false)
	private Boolean lastCheckStatus;

	/**
	 * Date when was some item added.
	 */
	@Column(name = "last_indexed_date", updatable = false)
	private Date lastIndexedDate;

	@Lob
	@Column(name = "last_check_error_text", length = Integer.MAX_VALUE, updatable = false)
	private String lastCheckErrorText;

	@Column(name = "last_check_error_count", updatable = false)
	private Integer lastCheckErrorCount;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private Category category;

	private Boolean aggregator;

	@Column(name = "min_reddit_ups")
	private Integer minRedditUps;

	@Column(updatable = false)
	private Integer popularity;

	@Column
	private Integer likes;

	private Boolean archived;

	public Boolean getArchived() {
		return archived;
	}

	public void setArchived(Boolean archived) {
		this.archived = archived;
	}

	public Integer getMinRedditUps() {
		return minRedditUps;
	}

	public void setMinRedditUps(Integer minRedditUps) {
		this.minRedditUps = minRedditUps;
	}

	public Integer getPopularity() {
		return popularity;
	}

	public void setPopularity(Integer popularity) {
		this.popularity = popularity;
	}

	public String getPublicName() {
		return MyUtil.getPublicName(nick, name, false);
	}

	public void setAggregator(Boolean aggregator) {
		this.aggregator = aggregator;
	}

	public Boolean getAggregator() {
		return aggregator;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Category getCategory() {
		return category;
	}

	public String getLastCheckErrorText() {
		return lastCheckErrorText;
	}

	public void setLastCheckErrorText(String lastCheckErrorText) {
		this.lastCheckErrorText = lastCheckErrorText;
	}

	public Integer getLastCheckErrorCount() {
		return lastCheckErrorCount;
	}

	public void setLastCheckErrorCount(Integer lastCheckErrorCount) {
		this.lastCheckErrorCount = lastCheckErrorCount;
	}

	public Boolean getLastCheckStatus() {
		return lastCheckStatus;
	}

	public void setLastCheckStatus(Boolean lastCheckStatus) {
		this.lastCheckStatus = lastCheckStatus;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getHomepageUrl() {
		return homepageUrl;
	}

	public void setHomepageUrl(String homepageUrl) {
		this.homepageUrl = homepageUrl;
	}

	public byte[] getIcon() {
		return icon;
	}

	public void setIcon(byte[] icon) {
		this.icon = icon;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public Date getLastIndexedDate() {
		return lastIndexedDate;
	}

	public void setLastIndexedDate(Date lastIndexedDate) {
		this.lastIndexedDate = lastIndexedDate;
	}

	public Integer getLikes() {
		return likes;
	}

	public void setLikes(Integer likes) {
		this.likes = likes;
	}

}
