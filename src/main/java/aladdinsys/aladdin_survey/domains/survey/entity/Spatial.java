/* (C) 2024 AladdinSystem License */
package aladdinsys.aladdin_survey.domains.survey.entity;

import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class Spatial {

  private Double x;
  private Double y;

  @Builder
  public Spatial(Double x, Double y) {
    this.x = x;
    this.y = y;
  }
}
