package RectangleIntersection.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Intersection {

  private Integer xCoord;
  private Integer yCoord;
  private Integer width;
  private Integer height;
  private List<Integer> intersectedRectangles;

  public Intersection(Integer xCoord, Integer yCoord, Integer width, Integer height, List<Integer> intersectedRectangles) {
    this.xCoord = xCoord;
    this.yCoord = yCoord;
    this.width = width;
    this.height = height;
    this.intersectedRectangles = intersectedRectangles;
  }

  public Integer getXCoord() {
    return xCoord;
  }

  public Integer getYCoord() {
    return yCoord;
  }

  public Integer getWidth() {
    return width;
  }

  public Integer getHeight() {
    return height;
  }

  public List<Integer> getIntersectedRectangles() {
    return intersectedRectangles;
  }

  public Intersection computeIntersection(List<Intersection> checkedIntersections) {
    List<Integer> topLeftXList = checkedIntersections.stream()
        .map(Intersection::getXCoord)
        .collect(Collectors.toList());

    topLeftXList.add(this.xCoord);

    List<Integer> topLeftYList = checkedIntersections.stream()
        .map(Intersection::getYCoord)
        .collect(Collectors.toList());

    topLeftYList.add(this.yCoord);

    List<Integer> widthList = checkedIntersections.stream()
        .map(checkedIntersection -> {
          return checkedIntersection.getXCoord() + checkedIntersection.getWidth();
        })
        .collect(Collectors.toList());

    widthList.add(this.xCoord + this.width);

    List<Integer> heightList = checkedIntersections.stream()
        .map(checkedIntersection -> {
          return checkedIntersection.getYCoord() + checkedIntersection.getHeight();
        })
        .collect(Collectors.toList());

    heightList.add(this.yCoord + this.height);

    Integer topLeftX = Collections.max(topLeftXList);
    Integer topLeftY = Collections.max(topLeftYList);
    Integer width = Collections.min(widthList) - topLeftX;
    Integer height = Collections.min(heightList) - topLeftY;

    List<Integer> dirtyRectangleIndex = new ArrayList<>();

    dirtyRectangleIndex.addAll(this.intersectedRectangles);

    checkedIntersections.stream()
        .forEach(checkedIntersection -> {
          dirtyRectangleIndex.addAll(checkedIntersection.getIntersectedRectangles());
        });

    List<Integer> cleanRectangeIndex = new ArrayList<>(
        new HashSet<>(dirtyRectangleIndex)
    );

    return new Intersection(topLeftX, topLeftY, width, height, cleanRectangeIndex);
  }
}
