package io.github.vouched;

import java.util.List;

public class CrossCheckIdentityPhone{
  public List<VouchedError> errors;
  public List<VouchedError> warnings;
  public Boolean isValid;
  public Boolean isMatch;
  public String name;
  public String type;
  public CrossCheckAgeRange ageRange;
  public String carrier;
  public Boolean isPrepaid;
  public Boolean isDisposable;
  public Boolean isCommercial;
}

