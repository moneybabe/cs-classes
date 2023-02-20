class Point {
    int x;
    int y;

    Point(int x, int y) {
      this.x = x;
      this.y = y;
    }
    boolean belowLeftOf(Point other) {
      return this.x <= other.x && this.y <= other.y;
    }
    boolean aboveRightOf(Point other) {
      return this.x >= other.x && this.y >= other.y;
    }
    double distance(Point other) {
      return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }
}  

class ClosestPoints {
    public static void main(String[] args) {

        int[] coordinateArr = new int[args.length];
        for (int i = 0; i < coordinateArr.length; i++) {
            coordinateArr[i] = Integer.parseInt(args[i]);
        }

        Point[] pointArr = new Point[args.length/2];
        for (int i = 0; i < pointArr.length; i++) {
            pointArr[i] = new Point(coordinateArr[2*i], coordinateArr[2*i+1]);
        }

        int distArrLength = 0;
        for (int i = pointArr.length - 1; i >= 1; i--)  {
            distArrLength += i;
        }

        double[] disArr = new double[distArrLength];
        int index = 0;
        for (int i = 0; i < disArr.length; i++) {
            for (int j = i+1; j < disArr.length; j++) {
                disArr[index] = pointArr[i].distance(pointArr[j]);
                index += 1;
            }
        }

        double shortestDist = disArr[0];
        for (int i = 1; i < pointArr.length; i++) {
            if (disArr[i] < shortestDist) {
                shortestDist = disArr[i];
            };
        }

        if (shortestDist == disArr[0]) {
            System.out.println("The closest points are (" + pointArr[0].x + ", " + pointArr[0].y
            + ") and (" + pointArr[1].x + ", " + pointArr[1].y + ") at distance " + shortestDist);
        } else if (shortestDist == disArr[1]) {
            System.out.println("The closest points are (" + pointArr[0].x + ", " + pointArr[0].y
            + ") and (" + pointArr[2].x + ", " + pointArr[2].y + ") at distance " + shortestDist);
        } else if (shortestDist == disArr[2]) {
            System.out.println("The closest points are (" + pointArr[1].x + ", " + pointArr[1].y
            + ") and (" + pointArr[2].x + ", " + pointArr[2].y + ") at distance " + shortestDist);
        } 



    }
}