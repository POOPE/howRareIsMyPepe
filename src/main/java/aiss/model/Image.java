package aiss.model;

public class Image {

    private String id;
    private String title;
    private String description;
    private String datetime;
    private String type;
    private Boolean animated;
    private Integer width;
    private Integer height;
    private Integer size;
    private Integer views;
    private Integer bandwidth;
    private Object vote;
    private Boolean favorite;
    private Boolean nsfw;
    private String section;
    private String account_url;
    private String account_id;
    private Boolean is_ad;
    private String ad_type;
    private String ad_url;
    private String[] tags;
    private Boolean in_most_viral;
    private Boolean in_gallery;
    private String deletehash;
    private String name;
    private String link;

    public Image() {}

    public Image(String id, String title, String description, String datetime, String type, Boolean animated, Integer width, Integer height, Integer size, Integer views, Integer bandwidth, Object vote, Boolean favorite, Boolean nsfw, String section, String account_url, String account_id, Boolean is_ad, String ad_type, String ad_url, String[] tags, Boolean in_most_viral, Boolean in_gallery, String deletehash, String name, String link) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.datetime = datetime;
        this.type = type;
        this.animated = animated;
        this.width = width;
        this.height = height;
        this.size = size;
        this.views = views;
        this.bandwidth = bandwidth;
        this.vote = vote;
        this.favorite = favorite;
        this.nsfw = nsfw;
        this.section = section;
        this.account_url = account_url;
        this.account_id = account_id;
        this.is_ad = is_ad;
        this.ad_type = ad_type;
        this.ad_url = ad_url;
        this.tags = tags;
        this.in_most_viral = in_most_viral;
        this.in_gallery = in_gallery;
        this.deletehash = deletehash;
        this.name = name;
        this.link = link;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getAnimated() {
        return animated;
    }

    public void setAnimated(Boolean animated) {
        this.animated = animated;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getBandwidth() {
        return bandwidth;
    }

    public void setBandwidth(Integer bandwidth) {
        this.bandwidth = bandwidth;
    }

    public Object getVote() {
        return vote;
    }

    public void setVote(Object vote) {
        this.vote = vote;
    }

    public Boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }

    public Boolean getNsfw() {
        return nsfw;
    }

    public void setNsfw(Boolean nsfw) {
        this.nsfw = nsfw;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getAccount_url() {
        return account_url;
    }

    public void setAccount_url(String account_url) {
        this.account_url = account_url;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public Boolean getIs_ad() {
        return is_ad;
    }

    public void setIs_ad(Boolean is_ad) {
        this.is_ad = is_ad;
    }

    public String getAd_type() {
        return ad_type;
    }

    public void setAd_type(String ad_type) {
        this.ad_type = ad_type;
    }

    public String getAd_url() {
        return ad_url;
    }

    public void setAd_url(String ad_url) {
        this.ad_url = ad_url;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public Boolean getIn_most_viral() {
        return in_most_viral;
    }

    public void setIn_most_viral(Boolean in_most_viral) {
        this.in_most_viral = in_most_viral;
    }

    public Boolean getIn_gallery() {
        return in_gallery;
    }

    public void setIn_gallery(Boolean in_gallery) {
        this.in_gallery = in_gallery;
    }

    public String getDeletehash() {
        return deletehash;
    }

    public void setDeletehash(String deletehash) {
        this.deletehash = deletehash;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
