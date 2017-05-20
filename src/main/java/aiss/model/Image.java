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
    private String[] tags;
    private Boolean in_most_viral;
    private Boolean in_gallery;
    private String deletehash;
    private String name;
    private String link;

    public Image() {}

    public Image(String id, String title, String description, String datetime, String type, Boolean animated, Integer width, Integer height, Integer size, Integer views, Integer bandwidth, Object vote, Boolean favorite, Boolean nsfw, String section, String account_url, String account_id, Boolean is_ad, String[] tags, Boolean in_most_viral, Boolean in_gallery, String deletehash, String name, String link) {
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

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDatetime() {
        return datetime;
    }

    public String getType() {
        return type;
    }

    public Boolean getAnimated() {
        return animated;
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getHeight() {
        return height;
    }

    public Integer getSize() {
        return size;
    }

    public Integer getViews() {
        return views;
    }

    public Integer getBandwidth() {
        return bandwidth;
    }

    public Object getVote() {
        return vote;
    }

    public Boolean getFavorite() {
        return favorite;
    }

    public Boolean getNsfw() {
        return nsfw;
    }

    public String getSection() {
        return section;
    }

    public String getAccount_url() {
        return account_url;
    }

    public String getAccount_id() {
        return account_id;
    }

    public Boolean getIs_ad() {
        return is_ad;
    }

    public String[] getTags() {
        return tags;
    }

    public Boolean getIn_most_viral() {
        return in_most_viral;
    }

    public Boolean getIn_gallery() {
        return in_gallery;
    }

    public String getDeletehash() {
        return deletehash;
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }
}
