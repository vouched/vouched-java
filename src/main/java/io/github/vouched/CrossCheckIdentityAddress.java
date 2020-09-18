package io.github.vouched;

import java.util.List;

public class CrossCheckIdentityAddress{
  public List<VouchedError> errors;
  public List<VouchedError> warnings;
  public Boolean isValid;
  public Boolean isMatch;
  public String name;
  public CrossCheckAgeRange ageRange;
  public Boolean isForwarder;
  public Boolean isCommercial;
  public String type;
}

