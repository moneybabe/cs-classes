import tester.*;
import java.util.List;
import java.util.Arrays;
import java.util.Comparator;
import java.util.ArrayList;

class Point {
  int x, y;
  Point(int x, int y) {
    this.x = x;
    this.y = y;
  }
  double distance(Point other) {
    return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
  }
}

class PointCompare implements Comparator<Point> {

  public int compare(Point a, Point b) {
    if (a.y < b.y) { return -1; }
    else if (a.y == b.y) {
      if (a.x < b.x) { return -1; }
      else if (a.x == b.x) { return 0; }
    }
    return 1;
  }
}

class PointDistanceCompare implements Comparator<Point> {

  public int compare(Point a, Point b) {
    Point origin = new Point(0,0);

    if (a.distance(origin) < b.distance(origin)) { return -1; }
    else if (a.distance(origin) > b.distance(origin)) { return 1; }
    return 0;
  }
}

class StringCompare implements Comparator<String> { 

  public int compare(String a, String b) {
    return a.compareTo(b);
  }
}

class StringLengthCompare implements Comparator<String> {

  public int compare(String a, String b) {    
  if (a.length() < b.length()) { return -1; }
  else if (a.length() > b.length()) { return 1; }
  return 0;
  }
}

class BooleanCompare implements Comparator<Boolean> {

  public int compare(Boolean a, Boolean b) {
    if (a) { if (b) return 0; return 1; } 
    else if (b) return -1;
    return 0;
  }
}











class CompareLists {

  <E> E minimum(List<E> list, Comparator<E> comp) {
    if (list.size() == 0) return null;

    E smallest = list.get(0);
    for (E i: list) {
      if (comp.compare(i, smallest) < 0) smallest = i;
    }
    return smallest;
  }
  
  <E> E minimum(E[] arr, Comparator<E> comp) {
    if (arr.length == 0) return null;

    E smallest = arr[0];
    for (E i: arr) {
      if (comp.compare(i, smallest) < 0) smallest = i;
    }
    return smallest;
  }

  <E> List<E> greaterThan(List<E> list, Comparator<E> comp, E compEl) {
    ArrayList<E> arr = new ArrayList<>();
    for (E i: list) {
      if (comp.compare(i, compEl) > 0) arr.add(i);
    }
    return arr;
  }

  <E> boolean inOrder(List<E> list, Comparator<E> comp) {
    if (list.size() == 0) return true;
    if (list.contains(null)) throw new IllegalArgumentException("null value in list");
    
    E currentIndex = list.get(0);
    for (E i: list) {
      if (comp.compare(currentIndex, i) > 0) return false;
      currentIndex = i;
    }
    return true;
  }

  <E> boolean inOrder(E[] arr, Comparator<E> comp) {
    if (arr.length == 0) return true;
    if (Arrays.asList(arr).contains(null)) throw new IllegalArgumentException("null value in list");

    E currentIndex = arr[0];
    for (E i: arr) {
      if (comp.compare(currentIndex, i) > 0) return false;
      currentIndex = i;
    }
    return true;
  }

  <E> List<E> merge(List<E> list1, List<E> list2, Comparator<E> comp) {
    if (list1.contains(null)) throw new  IllegalArgumentException("null value in first list");
    if (list2.contains(null)) throw new  IllegalArgumentException("null value in second list");

    return list1;

  }













  
  
  // test cases for comparators
  Comparator<Point> pointComp = new PointCompare();
  Point pt1 = new Point(5, 10);
  Point pt2 = new Point(2, 7);
  Point pt3 = new Point(0, -1);
  Point pt4 = new Point(-5, 10);
  void testPointCompare(Tester t) {
    t.checkExpect(this.pointComp.compare(this.pt2, this.pt1), -1);
    t.checkExpect(this.pointComp.compare(this.pt2, this.pt3), 1);
    t.checkExpect(this.pointComp.compare(this.pt4, this.pt1), -1);
    t.checkExpect(this.pointComp.compare(this.pt3, this.pt4), -1);
    t.checkExpect(this.pointComp.compare(this.pt4, this.pt4), 0);
  }
  
  Comparator<Point> pointDisComp = new PointDistanceCompare();
  void testPointDistanceCompare(Tester t) {
    t.checkExpect(this.pointDisComp.compare(this.pt2, this.pt1), -1);
    t.checkExpect(this.pointDisComp.compare(this.pt2, this.pt3), 1);
    t.checkExpect(this.pointDisComp.compare(this.pt4, this.pt1), 0);
    t.checkExpect(this.pointDisComp.compare(this.pt3, this.pt4), -1);
    t.checkExpect(this.pointDisComp.compare(this.pt4, this.pt4), 0);
  }
  
  Comparator<String> strComp = new StringCompare();
  String s1="hello";  
  String s2="hello";  
  String s3="meklo";  
  String s4="hemlo";  
  String s5="flag";  
  void testStringCompare(Tester t) {
    t.checkExpect(this.strComp.compare(this.s1, this.s2), 0);
    t.checkExpect(this.strComp.compare(this.s1, this.s3), -5);
    t.checkExpect(this.strComp.compare(this.s1, this.s4), -1);
    t.checkExpect(this.strComp.compare(this.s1, this.s5), 2); 
  }
  
  Comparator<String> strLenComp = new StringLengthCompare();
  void testStringLengthCompare(Tester t) {
    t.checkExpect(this.strLenComp.compare(this.s1, this.s2), 0);
    t.checkExpect(this.strLenComp.compare(this.s5, this.s3), -1);
    t.checkExpect(this.strLenComp.compare(this.s2, this.s2), 0);
    t.checkExpect(this.strLenComp.compare(this.s1, this.s5), 1);
  }
  
  Comparator<Boolean> booComp = new BooleanCompare();
  void testBooleanCompare(Tester t) {
    t.checkExpect(this.booComp.compare(true, false), 1);
    t.checkExpect(this.booComp.compare(false, false), 0);
    t.checkExpect(this.booComp.compare(false, true), -1);
    t.checkExpect(this.booComp.compare(true, true), 0);
    
  }

  















  // test cases for list methods
  List<String> list1 = new ArrayList<>(Arrays.asList("gm", "hi", "hello", "good morning", "halo", "bonjour", "hola"));
  List<Point> list2 = new ArrayList<>(Arrays.asList(this.pt1, this.pt2, this.pt3, this.pt4));
  List<Boolean> list3 = new ArrayList<>(Arrays.asList(true, false, false, false, true));

  void testMinimum1(Tester t) {
    t.checkExpect(this.minimum(list1, this.strComp), "bonjour");
    t.checkExpect(this.minimum(list3, this.booComp), false);
    t.checkExpect(this.minimum(list2, this.pointComp), this.pt3);
  }
  
  String[] arr1 = new String[]{"gm", "hi", "hello", "good morning", "halo", "bonjour", "hola"};
  Point[] arr2 = new Point[]{this.pt1, this.pt2, this.pt3, this.pt4};
  Boolean[] arr3 = new Boolean[]{true, false, false, false, true};
  void testMinimum2(Tester t) {
    t.checkExpect(this.minimum(arr1, this.strComp), "bonjour");
    t.checkExpect(this.minimum(arr3, this.booComp), false);
    t.checkExpect(this.minimum(arr2, this.pointComp), this.pt3);
  }

  void testGreaterThan(Tester t) {
    t.checkExpect(this.greaterThan(this.list1, this.strLenComp, "123"), new ArrayList<String>(Arrays.asList("hello", "good morning", "halo", "bonjour", "hola")));
    t.checkExpect(this.greaterThan(this.list2, this.pointComp, this.pt2), new ArrayList<Point>(Arrays.asList(this.pt1, this.pt4)));
    t.checkExpect(this.greaterThan(this.list3, this.booComp, false), new ArrayList<Boolean>(Arrays.asList(true, true)));
    t.checkExpect(this.greaterThan(this.list2, this.pointDisComp, new Point(2, 2)), new ArrayList<Point>(Arrays.asList(this.pt1, this.pt2, this.pt4)));
  }

  List<Point> list4 = new ArrayList<>(Arrays.asList(this.pt1, this.pt2, null, this.pt3, this.pt4));
  List<Point> list5 = new ArrayList<>(Arrays.asList(this.pt3, this.pt2, this.pt4, this.pt1));
  List<Point> list6 = new ArrayList<>(Arrays.asList(this.pt3, this.pt2, this.pt4, this.pt1));
  void testInOrder1(Tester t) {
    t.checkExpect(this.inOrder(this.list1, this.strLenComp), false);
    t.checkExpect(this.inOrder(this.list2, this.pointDisComp), false);
    t.checkExpect(this.inOrder(this.list3, this.booComp), false);
    t.checkExpect(this.inOrder(this.list5, this.pointComp), true);
    t.checkExpect(this.inOrder(this.list6, this.pointDisComp), true);
    t.checkException(new IllegalArgumentException("null value in list"), this, "inOrder", this.list4, this.pointComp);
  }
  
  Point[] arr4 = new Point[]{ this.pt1, this.pt3, this.pt2, null, this.pt4, this.pt1};
  void testInOrder2(Tester t) {
    t.checkExpect(this.inOrder(this.arr1, this.strLenComp), false);
    t.checkExpect(this.inOrder(this.arr2, this.pointDisComp), false);
    t.checkExpect(this.inOrder(this.arr3, this.booComp), false);
    t.checkException(new IllegalArgumentException("null value in list"), this, "inOrder", this.arr4, this.pointComp);
  }

  // void testMerge(Tester t) {
  //   t.checkExpect(this.merge(null, null, null), null)
  // }
}
