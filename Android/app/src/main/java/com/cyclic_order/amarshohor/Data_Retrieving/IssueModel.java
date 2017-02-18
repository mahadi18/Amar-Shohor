package com.cyclic_order.amarshohor.Data_Retrieving;

/**
 * Created by cyclic_order on 2/13/2016.
 */
public class IssueModel {
    String id,problem_title,latitude,longitude,posted_time,image,name,submitted_by;

    public String getId() {
        return id;
    }

    public String getSubmitted_by() {
        return submitted_by;
    }

    public void setSubmitted_by(String submitted_by) {
        this.submitted_by = submitted_by;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProblem_title() {
        return problem_title;
    }

    public void setProblem_title(String problem_title) {
        this.problem_title = problem_title;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getPosted_time() {
        return posted_time;
    }

    public void setPosted_time(String posted_time) {
        this.posted_time = posted_time;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
