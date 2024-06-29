package exercise;

class App {

    public static void main(String[] args) {
        // BEGIN
        SafetyList list = new SafetyList();
        list.add(5);
        list.add(7);
        System.out.println(list.get(0)); // 5
        System.out.println(list.get(1)); // 7
        System.out.println(list.getSize()); // 2

        ListThread thread = new ListThread(list);
        thread.run();
        System.out.println(list.getSize()); // 2000

        
        // END
    }
}

