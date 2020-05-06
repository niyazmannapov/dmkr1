import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(
                "transactions.csv"));

        String line = null;
        Scanner scanner = null;
        int index = 0;
        ArrayList<Product> products = new ArrayList<Product>();
        while ((line = reader.readLine()) != null) {
            Product prod = new Product();
            scanner = new Scanner(line);
            scanner.useDelimiter(";");
            while (scanner.hasNext()) {
                String data = scanner.next();
                if (index == 0)
                    prod.setId(data);
                else if (index == 1)
                    prod.setBasket(data);
                index++;
            }
            index = 0;
            products.add(prod);
        }

        ArrayList<ArrayList<Product>> baskets = new ArrayList<ArrayList<Product>>();
        ArrayList set = new ArrayList();
        String previousId = "q";
        for (Product product : products) {
            if (product.getBasket().equals(previousId)) {
                set.add(product);
            } else {
                baskets.add(set);
                set = new ArrayList();
                set.add(product);
                previousId = product.getBasket();
            }
        }
        baskets.remove(0);
        baskets.remove(0);
        ArrayList<ArrayList<Pair>> pairs = new ArrayList<>();
        int t = 0;
        Pair pair = null;
        for (ArrayList<Product> basket : baskets) {
            ArrayList<Pair> pairsArrayList = new ArrayList<>();
            for (int i = 0; i < basket.size() - 1; i++) {
                for (int j = i + 1; j < basket.size(); j++) {
                    pair = new Pair();
                    pair.setProduct1(basket.get(i));
                    pair.setProduct2(basket.get(j));
                    pairsArrayList.add(pair);
                }
            }
            pairs.add(pairsArrayList);
        }

        int count = 0;
        String id;
        for (int i = 0; i < products.size() - 1; i++) {
            id = products.get(i).getId();
            for (int j = i + 1; j < products.size(); j++) {
                if (id.equals(products.get(j).getId())) {
                    products.get(i).inc();
                    products.get(j).inc();
                }
            }
        }
        int d= 0;
        for (Product product: products) {
            if (product.getId().equals("PRD0903678")) {
                d++;
            }
        }
        System.out.println(d);
        ArrayList<ArrayList<Pair>> groupOne = new ArrayList<>();
        ArrayList<ArrayList<Pair>> groupTwo = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            groupOne.add(new ArrayList<>());
            groupTwo.add(new ArrayList<>());
        }
        for (ArrayList<Pair> pairArrayList : pairs) {
            for (Pair pair1 : pairArrayList) {
                groupOne.get(pair1.getHash1()).add(pair1);
                groupTwo.get(pair1.getHash2()).add(pair1);
            }
        }

        boolean[][] booleans = new boolean[2][5];
        for (int i = 0; i < 1; i++) {
            int groupSize = 0;
            for (int j = 0; j < 5; j++) {
                for (ArrayList<Pair> pairs1 : groupOne) {
                    groupSize = pairs1.size();
                }
                if (groupSize >= 4) {
                    booleans[i][j] = true;
                }
            }
        }
        for (int i = 1; i < 2; i++) {
            int groupSize = 0;
            for (int j = 0; j < 5; j++) {
                for (ArrayList<Pair> pairs1 : groupTwo) {
                    groupSize = pairs1.size();
                }
                if (groupSize >= 4) {
                    booleans[i][j] = true;
                }
            }
        }
        int x = 0;
        for (ArrayList<Pair> pairArrayList : pairs) {
            for (Pair pair1 : pairArrayList) {
                if (((booleans[0][pair1.getHash1()])) && booleans[1][pair1.getHash2()] && pair1.goodPair()){
                    x++;
                }
            }
        }
        System.out.println(x);;
    }
}
