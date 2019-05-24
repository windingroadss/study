package generic.wildcard;

public class CarWildcardSample {
    public static void main(String[] ar) {
        CarWildcardSample ex = new CarWildcardSample();
        ex.callBoundedWildcardMethod();
        ex.callBusBoundedWildcardMethod();

        WildCardGeneric<Long> w = new WildCardGeneric("long");
        Bus bus = new Bus("Toyota bus");
        String result = ex.genericMethod(w, "WildCardGeneric", bus);
        System.out.println(result);
    }

    public void callBoundedWildcardMethod() {
        WildCardGeneric<Car> wildcard = new WildCardGeneric<Car>();
        wildcard.setWildCard(new Car("Mustang"));
        boundedWildcardMethod(wildcard);
        // Car name = Mustang
    }

    /**
     * Car class 와 Car class 를 상속받은 클래스가 param으로 올 수 있음
     * @param c : Car or Car class's child class
     */
    public void boundedWildcardMethod(WildCardGeneric<? extends Car> c) {
        Car value = c.getWildCard();
        System.out.println(value);
    }

    public void callBusBoundedWildcardMethod() {
        WildCardGeneric<Bus> wildcard = new WildCardGeneric<Bus>();
        wildcard.setWildCard(new Bus("7777"));
        boundedWildcardMethod(wildcard);
        // Bus name = 7777
    }

    public <S, T extends Car> S genericMethod(WildCardGeneric<?> c, S addValue1, T addValue2) {
        System.out.println("##############genericMethod##############");

        System.out.println(c.getWildCard());
        System.out.println(String.valueOf(addValue1));
        System.out.println(String.valueOf(addValue2));

        System.out.println("##############genericMethod##############");

        if (addValue1 instanceof String) { return (S) (addValue1 + " called"); } else { return addValue1; }
    }
}
