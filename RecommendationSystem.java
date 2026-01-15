import java.util.*;

public class RecommendationSystem {

    // User Class
    static class User {
        int userId;
        String name;

        User(int userId, String name) {
            this.userId = userId;
            this.name = name;
        }
    }

    // Product Class
    static class Product {
        int productId;
        String name;

        Product(int productId, String name) {
            this.productId = productId;
            this.name = name;
        }
    }

    // Rating Class
    static class Rating {
        int userId;
        int productId;
        double rating;

        Rating(int userId, int productId, double rating) {
            this.userId = userId;
            this.productId = productId;
            this.rating = rating;
        }
    }

    // Recommendation Engine (AI Logic)
    static class RecommendationEngine {
        List<Rating> ratings;

        RecommendationEngine(List<Rating> ratings) {
            this.ratings = ratings;
        }

        // Get ratings of a specific user
        private Map<Integer, Double> getUserRatings(int userId) {
            Map<Integer, Double> map = new HashMap<>();
            for (Rating r : ratings) {
                if (r.userId == userId) {
                    map.put(r.productId, r.rating);
                }
            }
            return map;
        }

        // Find similarity between two users
        private double similarity(int u1, int u2) {
            Map<Integer, Double> user1 = getUserRatings(u1);
            Map<Integer, Double> user2 = getUserRatings(u2);

            double sum = 0;
            int count = 0;

            for (int p : user1.keySet()) {
                if (user2.containsKey(p)) {
                    sum += 1 - Math.abs(user1.get(p) - user2.get(p)) / 5.0;
                    count++;
                }
            }
            return count == 0 ? 0 : sum / count;
        }

        // Generate Recommendations
        List<Integer> recommend(int userId) {
            Map<Integer, Double> scores = new HashMap<>();

            for (Rating r : ratings) {
                int otherUser = r.userId;
                if (otherUser != userId) {
                    double sim = similarity(userId, otherUser);

                    if (!getUserRatings(userId).containsKey(r.productId)) {
                        scores.put(r.productId,
                                scores.getOrDefault(r.productId, 0.0) + sim * r.rating);
                    }
                }
            }

            List<Integer> result = new ArrayList<>(scores.keySet());
            result.sort((a, b) -> Double.compare(scores.get(b), scores.get(a)));
            return result;
        }
    }

    // Main Method
    public static void main(String[] args) {

        // Products
        List<Product> products = Arrays.asList(
                new Product(101, "Laptop"),
                new Product(102, "Mobile"),
                new Product(103, "Tablet"),
                new Product(104, "Headphones")
        );

        // Sample Ratings
        List<Rating> ratings = Arrays.asList(
                new Rating(1, 101, 5),
                new Rating(1, 102, 3),
                new Rating(2, 101, 4),
                new Rating(2, 103, 5),
                new Rating(3, 102, 4),
                new Rating(3, 104, 5)
        );

        RecommendationEngine engine = new RecommendationEngine(ratings);

        // Get Recommendations for User 1
        List<Integer> recommendations = engine.recommend(1);

        System.out.println("Recommended Products for User 1:");
        for (int id : recommendations) {
            for (Product p : products) {
                if (p.productId == id) {
                    System.out.println("👉 " + p.name);
                }
            }
        }
    }
}
