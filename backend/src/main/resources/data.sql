-- Insert default admin user (password: admin123)
INSERT INTO users (username, password, role, phone, email, created_at, updated_at)
VALUES ('admin', 'admin123', 'ADMIN', '13800138000', 'admin@flowershop.com', NOW(), NOW());

-- Insert default regular user (password: user123)
INSERT INTO users (username, password, role, phone, email, created_at, updated_at)
VALUES ('user', 'user123', 'USER', '13900139000', 'user@example.com', NOW(), NOW());

-- Insert categories
INSERT INTO categories (name, description, sort_order, created_at, updated_at)
VALUES ('玫瑰系列', '经典玫瑰，多种颜色与规格', 1, NOW(), NOW());
INSERT INTO categories (name, description, sort_order, created_at, updated_at)
VALUES ('百合系列', '优雅百合，芳香四溢', 2, NOW(), NOW());
INSERT INTO categories (name, description, sort_order, created_at, updated_at)
VALUES ('郁金香系列', '多彩郁金香，四季绽放', 3, NOW(), NOW());
INSERT INTO categories (name, description, sort_order, created_at, updated_at)
VALUES ('向日葵系列', '明亮欢快的向日葵', 4, NOW(), NOW());
INSERT INTO categories (name, description, sort_order, created_at, updated_at)
VALUES ('兰花系列', '高雅兰花，异域风情', 5, NOW(), NOW());
INSERT INTO categories (name, description, sort_order, created_at, updated_at)
VALUES ('混搭花束', '精美搭配的混搭花束', 6, NOW(), NOW());
INSERT INTO categories (name, description, sort_order, created_at, updated_at)
VALUES ('康乃馨系列', '温馨康乃馨，传递真情', 7, NOW(), NOW());
INSERT INTO categories (name, description, sort_order, created_at, updated_at)
VALUES ('特色花卉', '其他特色精品花卉', 8, NOW(), NOW());

-- Insert flowers
INSERT INTO flowers (name, description, price, image_url, stock_status, category_id, color, language, created_at, updated_at)
VALUES ('红玫瑰', '经典红玫瑰，象征热烈爱情。每枝都经过精心挑选，色泽鲜艳，花型饱满。', 59.90, '/images/rose-red.svg', 'IN_STOCK', 1, '红色', '热情的爱', NOW(), NOW());
INSERT INTO flowers (name, description, price, image_url, stock_status, category_id, color, language, created_at, updated_at)
VALUES ('白玫瑰', '纯洁白玫瑰，代表纯真与优雅。婚礼首选花卉。', 49.90, '/images/rose-white.svg', 'IN_STOCK', 1, '白色', '纯洁无瑕', NOW(), NOW());
INSERT INTO flowers (name, description, price, image_url, stock_status, category_id, color, language, created_at, updated_at)
VALUES ('粉玫瑰', '甜美粉玫瑰，表达感激与倾慕之情。', 54.90, '/images/rose-pink.svg', 'IN_STOCK', 1, '粉色', '甜蜜的爱', NOW(), NOW());
INSERT INTO flowers (name, description, price, image_url, stock_status, category_id, color, language, created_at, updated_at)
VALUES ('东方百合', '芳香东方百合，粉白花瓣惊艳绽放。', 69.90, '/images/lily-oriental.svg', 'IN_STOCK', 2, '粉白', '高贵纯洁', NOW(), NOW());
INSERT INTO flowers (name, description, price, image_url, stock_status, category_id, color, language, created_at, updated_at)
VALUES ('白百合', '优雅白百合，清新芬芳沁人心脾。', 59.90, '/images/lily-white.svg', 'LOW_STOCK', 2, '白色', '甜美优雅', NOW(), NOW());
INSERT INTO flowers (name, description, price, image_url, stock_status, category_id, color, language, created_at, updated_at)
VALUES ('红郁金香', '鲜艳红郁金香，爱的宣言。', 39.90, '/images/tulip-red.svg', 'IN_STOCK', 3, '红色', '爱的宣言', NOW(), NOW());
INSERT INTO flowers (name, description, price, image_url, stock_status, category_id, color, language, created_at, updated_at)
VALUES ('黄郁金香', '明亮黄郁金香，为房间带来阳光。', 35.90, '/images/tulip-yellow.svg', 'IN_STOCK', 3, '黄色', '快乐与希望', NOW(), NOW());
INSERT INTO flowers (name, description, price, image_url, stock_status, category_id, color, language, created_at, updated_at)
VALUES ('向日葵', '金黄向日葵，追逐阳光。象征幸福快乐。', 45.90, '/images/sunflower.svg', 'IN_STOCK', 4, '黄色', '崇敬与忠诚', NOW(), NOW());
INSERT INTO flowers (name, description, price, image_url, stock_status, category_id, color, language, created_at, updated_at)
VALUES ('迷你向日葵', '小巧可爱的迷你向日葵，适合小空间。', 29.90, '/images/sunflower-mini.svg', 'OUT_OF_STOCK', 4, '黄色', '幸福与活力', NOW(), NOW());
INSERT INTO flowers (name, description, price, image_url, stock_status, category_id, color, language, created_at, updated_at)
VALUES ('蝴蝶兰', '优雅蝴蝶兰，花型优美，花期持久。', 129.90, '/images/orchid-phalaenopsis.svg', 'IN_STOCK', 5, '白紫', '美丽与优雅', NOW(), NOW());
INSERT INTO flowers (name, description, price, image_url, stock_status, category_id, color, language, created_at, updated_at)
VALUES ('大花蕙兰', '异域大花蕙兰，每枝多花绽放。', 149.90, '/images/orchid-cymbidium.svg', 'IN_STOCK', 5, '绿粉', '美德与高尚', NOW(), NOW());
INSERT INTO flowers (name, description, price, image_url, stock_status, category_id, color, language, created_at, updated_at)
VALUES ('浪漫花束', '红玫瑰搭配白百合与满天星的浪漫组合。', 199.90, '/images/bouquet-romance.svg', 'IN_STOCK', 6, '混色', '浪漫与爱情', NOW(), NOW());
INSERT INTO flowers (name, description, price, image_url, stock_status, category_id, color, language, created_at, updated_at)
VALUES ('阳光花束', '黄郁金香与向日葵组合，明亮欢快。', 169.90, '/images/bouquet-sunshine.svg', 'IN_STOCK', 6, '黄绿', '欢乐与友谊', NOW(), NOW());
INSERT INTO flowers (name, description, price, image_url, stock_status, category_id, color, language, created_at, updated_at)
VALUES ('优雅花束', '白玫瑰、百合与兰花的优雅白色花束。', 249.90, '/images/bouquet-elegance.svg', 'LOW_STOCK', 6, '白色', '优雅与纯洁', NOW(), NOW());
INSERT INTO flowers (name, description, price, image_url, stock_status, category_id, color, language, created_at, updated_at)
VALUES ('康乃馨', '温馨康乃馨，传递对母亲的爱与感恩。', 39.90, '/images/flower-carnation.svg', 'IN_STOCK', 7, '粉色', '母爱与感恩', NOW(), NOW());
INSERT INTO flowers (name, description, price, image_url, stock_status, category_id, color, language, created_at, updated_at)
VALUES ('绣球花', '饱满绣球花，色彩缤纷，花团锦簇。', 79.90, '/images/flower-hydrangea.svg', 'IN_STOCK', 8, '蓝色', '团圆美满', NOW(), NOW());
INSERT INTO flowers (name, description, price, image_url, stock_status, category_id, color, language, created_at, updated_at)
VALUES ('牡丹', '国色天香的牡丹，花中之王，雍容华贵。', 89.90, '/images/flower-peony.svg', 'IN_STOCK', 8, '粉色', '富贵吉祥', NOW(), NOW());
INSERT INTO flowers (name, description, price, image_url, stock_status, category_id, color, language, created_at, updated_at)
VALUES ('薰衣草', '芳香薰衣草，放松身心，宁静致远。', 35.90, '/images/flower-lavender.svg', 'IN_STOCK', 8, '紫色', '宁静与浪漫', NOW(), NOW());
INSERT INTO flowers (name, description, price, image_url, stock_status, category_id, color, language, created_at, updated_at)
VALUES ('雏菊', '清新小雏菊，天真烂漫，充满活力。', 25.90, '/images/flower-daisy.svg', 'IN_STOCK', 8, '白色', '天真与希望', NOW(), NOW());
INSERT INTO flowers (name, description, price, image_url, stock_status, category_id, color, language, created_at, updated_at)
VALUES ('荷花', '出淤泥而不染的荷花，清雅高洁。', 69.90, '/images/flower-lotus.svg', 'IN_STOCK', 8, '粉色', '高洁与清廉', NOW(), NOW());
