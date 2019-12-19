package com.vouched.sdk;

import java.util.List;

class Jobs {

  private List<Job> jobs;

  private int page;
  private int pageSize;
  private int totalPages;
  private int total;

  public int getPage() {
    return this.page;
  }

  public void setPage(int page) {
    this.page = page;
  }

  public int getPageSize() {
    return this.pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public int getTotalPages() {
    return this.totalPages;
  }

  public void setTotalPages(int totalPages) {
    this.totalPages = totalPages;
  }

  public int getTotal() {
    return this.total;
  }

  public void setTotal(int total) {
    this.total = total;
  }

  public List<Job> getJobs() {
    return jobs;
  }


  public void setJobs(List<Job> jobs) {
    this.jobs = jobs;
  }
}