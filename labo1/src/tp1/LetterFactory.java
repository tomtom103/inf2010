package tp1;

public final class LetterFactory {
    final static Double maxHeight = 200.0;
    final static Double maxWidth = maxHeight / 2.5;
    final static Double halfMaxHeight = maxHeight / 2;
    final static Double halfMaxWidth = maxWidth / 2;
    final static Double stripeThickness = maxHeight / 8;
    final static Double halfStripeThickness = stripeThickness / 2;

    // TODO
    public static BaseShape create_e() {
        BaseShape mainBody = new Ellipse(maxWidth, maxHeight);
        BaseShape middleToRemove = new Ellipse(maxWidth - stripeThickness, maxHeight - stripeThickness);

        BaseShape middlePart = new Rectangle(maxWidth, halfStripeThickness);
        BaseShape rightToRremove = new Rectangle(halfMaxWidth, maxHeight/4).translate(new Point2d(halfMaxWidth/2, 30.0));

        mainBody.remove(middleToRemove);
        mainBody.remove(rightToRremove);
        mainBody.add(middlePart);
        return mainBody;
    }

    // TODO
    public static BaseShape create_a() {
        BaseShape mainBody = new Ellipse(maxWidth, maxHeight);
        BaseShape middleToRemove = new Ellipse(maxWidth - stripeThickness, maxHeight - stripeThickness);
        BaseShape rightPart = new Rectangle(halfStripeThickness, maxHeight).translate(new Point2d(maxWidth / 2, 0.0));

        mainBody.remove(middleToRemove);
        mainBody.add(rightPart);
        return mainBody;
    }

    public static BaseShape create_C() {
        BaseShape mainBody = new Ellipse(maxWidth, maxHeight);
        Ellipse middleToRemove = new Ellipse(maxWidth - stripeThickness,
                maxHeight - stripeThickness);
        BaseShape rightToRremove = new Rectangle(halfMaxWidth, maxHeight/1.5).translate(new Point2d(halfMaxWidth/2, 0.0));

        mainBody.remove(middleToRemove);
        mainBody.remove(rightToRremove);
        return mainBody;
    }

    // TODO
    public static BaseShape create_l() {
        BaseShape mainBody = new Rectangle(halfStripeThickness, maxHeight);
        return mainBody;
    }

    // TODO
    public static BaseShape create_i() {
        BaseShape mainBody = new Circle(stripeThickness);
        BaseShape bottomBar = new Rectangle(halfStripeThickness, maxHeight / 1.3).translate(new Point2d(0.0, 110.0));
        mainBody.add(bottomBar);
        return mainBody;
    }

    // TODO
    public static BaseShape create_A() {
        BaseShape mainBody = new Rectangle(halfStripeThickness, maxHeight).rotate(Math.toRadians(10.0));
        BaseShape secondBar = new Rectangle(halfStripeThickness, maxHeight).rotate(Math.toRadians(-10.0)).translate(new Point2d(40.0, 0.0));
        BaseShape middleBar = new Rectangle(halfStripeThickness, maxHeight / 4).rotate(Math.toRadians(90.0)).translate(new Point2d(20.0, 0.0));
        mainBody.add(middleBar);
        mainBody.add(secondBar);
        return mainBody;
    }

    // TODO
    public static BaseShape create_V() {
        BaseShape mainBody = new Rectangle(halfStripeThickness, maxHeight).rotate(Math.toRadians(10.0));
        BaseShape secondBar = new Rectangle(halfStripeThickness, maxHeight).rotate(Math.toRadians(-10.0)).translate(new Point2d(-halfMaxHeight / 2 + 10.0, 0.0));
        mainBody.add(secondBar);
        return mainBody;
    }

    // TODO
    public static BaseShape create_n() {
        BaseShape mainBody = new Circle(maxHeight / 2);
        BaseShape removeMiddle = new Circle(maxHeight / 2 - stripeThickness);
        BaseShape removeHalf = new Square(maxHeight / 2).translate(new Point2d(0.0, halfMaxHeight / 2));
        BaseShape sidePiece = new Rectangle(halfStripeThickness, maxHeight).translate(new Point2d(-halfMaxHeight / 2.3, halfMaxHeight / 2));
        BaseShape rightSidePiece = new Rectangle(halfStripeThickness, maxHeight / 1.2).translate(new Point2d(halfMaxHeight / 2.4, halfMaxHeight / 1.5));

        mainBody.remove(removeMiddle);
        mainBody.remove(removeHalf);
        mainBody.add(sidePiece);
        mainBody.add(rightSidePiece);
        return mainBody;
    }

    // TODO
    public static BaseShape create_r() {
        BaseShape mainBody = new Circle(maxHeight / 2);
        BaseShape removeMiddle = new Circle(maxHeight / 2 - stripeThickness);
        BaseShape removeHalf = new Square(maxHeight / 2).translate(new Point2d(0.0, halfMaxHeight / 2));
        BaseShape sidePiece = new Rectangle(halfStripeThickness, maxHeight).translate(new Point2d(-halfMaxHeight / 2.2, halfMaxHeight / 2));

        mainBody.remove(removeMiddle);
        mainBody.remove(removeHalf);
        mainBody.add(sidePiece);
        return mainBody;

    }

    // TODO
    public static BaseShape create_B() {
        BaseShape mainBody = new Circle(maxHeight / 2);
        BaseShape secondHoop = new Circle(maxHeight / 2).translate(new Point2d(0.0, -maxHeight / 2));
        BaseShape removeCenter = new Circle(maxHeight / 2 - stripeThickness);
        BaseShape removeSecond = new Circle(maxHeight / 2 - stripeThickness).translate(new Point2d(0.0, -maxHeight / 2));
        mainBody.add(secondHoop);
        mainBody.remove(removeCenter);
        mainBody.remove(removeSecond);
        BaseShape removeLeft = new Rectangle(maxHeight / 4, maxHeight).translate(new Point2d(-maxWidth / 2.5, -50.0));
        BaseShape addLeft = new Rectangle(halfStripeThickness, maxHeight).translate(new Point2d(-halfStripeThickness, -halfMaxHeight / 2));
        mainBody.remove(removeLeft);
        mainBody.add(addLeft);
        return mainBody;
    }
}
