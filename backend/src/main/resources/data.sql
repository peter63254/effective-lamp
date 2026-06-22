-- Insert default admin user (password: admin123)
INSERT INTO users (username, password, role, phone, email, created_at, updated_at)
VALUES ('admin', 'admin123', 'ADMIN', '13800138000', 'admin@flowershop.com', NOW(), NOW());

-- Insert default regular user (password: user123)
INSERT INTO users (username, password, role, phone, email, created_at, updated_at)
VALUES ('user', 'user123', 'USER', '13900139000', 'user@example.com', NOW(), NOW());

-- Insert categories
INSERT INTO categories (name, description, sort_order, created_at, updated_at)
VALUES ('Rose Series', 'Classic roses in various colors and sizes', 1, NOW(), NOW());

INSERT INTO categories (name, description, sort_order, created_at, updated_at)
VALUES ('Lily Series', 'Elegant lilies with beautiful fragrances', 2, NOW(), NOW());

INSERT INTO categories (name, description, sort_order, created_at, updated_at)
VALUES ('Tulip Series', 'Colorful tulips for every season', 3, NOW(), NOW());

INSERT INTO categories (name, description, sort_order, created_at, updated_at)
VALUES ('Sunflower Series', 'Bright and cheerful sunflowers', 4, NOW(), NOW());

INSERT INTO categories (name, description, sort_order, created_at, updated_at)
VALUES ('Orchid Series', 'Exotic and elegant orchids', 5, NOW(), NOW());

INSERT INTO categories (name, description, sort_order, created_at, updated_at)
VALUES ('Mixed Bouquets', 'Beautifully arranged mixed flower bouquets', 6, NOW(), NOW());

-- Insert flowers (using H2 compatible syntax)
INSERT INTO flowers (name, description, price, image_url, stock_status, category_id, color, language, created_at, updated_at)
VALUES ('Red Rose', 'Classic red rose, symbol of love and passion. Each stem is carefully selected for its vibrant color and freshness.', 59.90, '/images/rose-red.svg', 'IN_STOCK', 1, 'Red', 'Love and passion', NOW(), NOW());

INSERT INTO flowers (name, description, price, image_url, stock_status, category_id, color, language, created_at, updated_at)
VALUES ('White Rose', 'Pure white rose representing innocence and elegance. Perfect for weddings.', 49.90, '/images/rose-white.svg', 'IN_STOCK', 1, 'White', 'Purity and innocence', NOW(), NOW());

INSERT INTO flowers (name, description, price, image_url, stock_status, category_id, color, language, created_at, updated_at)
VALUES ('Pink Rose', 'Sweet pink rose expressing gratitude and admiration.', 54.90, '/images/rose-pink.svg', 'IN_STOCK', 1, 'Pink', 'Grace and gratitude', NOW(), NOW());

INSERT INTO flowers (name, description, price, image_url, stock_status, category_id, color, language, created_at, updated_at)
VALUES ('Oriental Lily', 'Fragrant oriental lily with stunning pink-white petals.', 69.90, '/images/lily-oriental.svg', 'IN_STOCK', 2, 'Pink-White', 'Noble and pure', NOW(), NOW());

INSERT INTO flowers (name, description, price, image_url, stock_status, category_id, color, language, created_at, updated_at)
VALUES ('White Lily', 'Elegant white lily with a heavenly fragrance.', 59.90, '/images/lily-white.svg', 'LOW_STOCK', 2, 'White', 'Sweet and elegant', NOW(), NOW());

INSERT INTO flowers (name, description, price, image_url, stock_status, category_id, color, language, created_at, updated_at)
VALUES ('Red Tulip', 'Vibrant red tulip, a declaration of love.', 39.90, '/images/tulip-red.svg', 'IN_STOCK', 3, 'Red', 'Declaration of love', NOW(), NOW());

INSERT INTO flowers (name, description, price, image_url, stock_status, category_id, color, language, created_at, updated_at)
VALUES ('Yellow Tulip', 'Bright yellow tulip bringing sunshine to any room.', 35.90, '/images/tulip-yellow.svg', 'IN_STOCK', 3, 'Yellow', 'Cheerfulness and hope', NOW(), NOW());

INSERT INTO flowers (name, description, price, image_url, stock_status, category_id, color, language, created_at, updated_at)
VALUES ('Sunflower', 'Large golden sunflower that follows the sun. A symbol of happiness.', 45.90, '/images/sunflower.svg', 'IN_STOCK', 4, 'Yellow', 'Adoration and loyalty', NOW(), NOW());

INSERT INTO flowers (name, description, price, image_url, stock_status, category_id, color, language, created_at, updated_at)
VALUES ('Mini Sunflower', 'Cute miniature sunflower perfect for small spaces.', 29.90, '/images/sunflower-mini.svg', 'OUT_OF_STOCK', 4, 'Yellow', 'Happiness and energy', NOW(), NOW());

INSERT INTO flowers (name, description, price, image_url, stock_status, category_id, color, language, created_at, updated_at)
VALUES ('Phalaenopsis Orchid', 'Elegant moth orchid with long-lasting blooms.', 129.90, '/images/orchid-phalaenopsis.svg', 'IN_STOCK', 5, 'White-Purple', 'Beauty and refinement', NOW(), NOW());

INSERT INTO flowers (name, description, price, image_url, stock_status, category_id, color, language, created_at, updated_at)
VALUES ('Cymbidium Orchid', 'Exotic cymbidium orchid with multiple blooms per stem.', 149.90, '/images/orchid-cymbidium.svg', 'IN_STOCK', 5, 'Green-Pink', 'Virtue and morality', NOW(), NOW());

INSERT INTO flowers (name, description, price, image_url, stock_status, category_id, color, language, created_at, updated_at)
VALUES ('Romance Bouquet', 'A romantic mix of red roses, white lilies and baby breath.', 199.90, '/images/bouquet-romance.svg', 'IN_STOCK', 6, 'Mixed', 'Romance and love', NOW(), NOW());

INSERT INTO flowers (name, description, price, image_url, stock_status, category_id, color, language, created_at, updated_at)
VALUES ('Sunshine Bouquet', 'Bright and cheerful mix of yellow tulips, sunflowers and green foliage.', 169.90, '/images/bouquet-sunshine.svg', 'IN_STOCK', 6, 'Yellow-Green', 'Joy and friendship', NOW(), NOW());

INSERT INTO flowers (name, description, price, image_url, stock_status, category_id, color, language, created_at, updated_at)
VALUES ('Elegance Bouquet', 'An elegant white-themed bouquet with white roses, lilies and orchids.', 249.90, '/images/bouquet-elegance.svg', 'LOW_STOCK', 6, 'White', 'Elegance and purity', NOW(), NOW());
