package io.github.vouched;

import java.util.List;

public class CrossCheckIdentityAddress{
  public CrossCheckErrorAddress [] errors;
  public CrossCheckWarningAddress [] warnings;
  public boolean isValid;
  public boolean isMatch;
  public String name;
  public CrossCheckAgeRangeAddress ageRange;
  public boolean isForwarder;
  public boolean isCommercial;
  public String type;
}

