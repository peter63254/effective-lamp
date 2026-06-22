package com.flowershop.config;

import com.flowershop.model.entity.Category;
import com.flowershop.model.entity.Flower;
import com.flowershop.model.entity.User;
import com.flowershop.repository.CategoryRepository;
import com.flowershop.repository.FlowerRepository;
import com.flowershop.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class DataInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final FlowerRepository flowerRepository;
    public DataInitializer(UserRepository ur, CategoryRepository cr, FlowerRepository fr) {
        this.userRepository = ur; this.categoryRepository = cr; this.flowerRepository = fr;
    }
    @Override
    public void run(String... args) {
        if (userRepository.count() > 0) return;
        User admin = new User(); admin.setUsername("admin"); admin.setPassword("admin123");
        admin.setRole("ADMIN"); admin.setPhone("13800138000"); admin.setEmail("admin@flowershop.com");
        userRepository.save(admin);
        User user = new User(); user.setUsername("user"); user.setPassword("user123");
        user.setRole("USER"); user.setPhone("13900139000"); user.setEmail("user@example.com");
        userRepository.save(user);
        String[][] cats = {{"玫瑰花","经典玫瑰"},{"百合花","优雅百合"},{"郁金香","多彩郁金香"},{"向日葵","明亮向日葵"},{"兰花","高雅兰花"},{"混搭花束","精美花束"},{"康乃馨","温馨康乃馨"},{"特色花卉","其他特色花卉"}};
        Category[] c = new Category[cats.length];
        for(int i=0;i<cats.length;i++){c[i]=new Category();c[i].setName(cats[i][0]);c[i].setDescription(cats[i][1]);c[i].setSortOrder(i+1);categoryRepository.save(c[i]);}
        saveFlower(flowerRepository,c,0,"红玫瑰","经典红玫瑰，象征热烈爱情",59.90,"/images/rose-red.svg","红色","热烈的爱");
        saveFlower(flowerRepository,c,0,"白玫瑰","纯洁白玫瑰，代表纯真与优雅",49.90,"/images/rose-white.svg","白色","纯洁无瑕");
        saveFlower(flowerRepository,c,0,"粉玫瑰","甜美粉玫瑰，表达感激与倾慕",54.90,"/images/rose-pink.svg","粉色","甜蜜的爱");
        saveFlower(flowerRepository,c,1,"东方百合","芳香东方百合，粉白花瓣惊艳绽放",69.90,"/images/lily-oriental.svg","粉白","高贵纯洁");
        saveFlower(flowerRepository,c,1,"白百合","优雅白百合，清新芬芳",59.90,"/images/lily-white.svg","白色","甜美优雅","LOW_STOCK");
        saveFlower(flowerRepository,c,2,"红郁金香","鲜艳红郁金香，爱的宣言",39.90,"/images/tulip-red.svg","红色","爱的宣言");
        saveFlower(flowerRepository,c,2,"黄郁金香","明亮黄郁金香，带来阳光",35.90,"/images/tulip-yellow.svg","黄色","快乐与希望");
        saveFlower(flowerRepository,c,3,"向日葵","金黄向日葵，追逐阳光象征幸福",45.90,"/images/sunflower.svg","黄色","崇敬与忠诚");
        saveFlower(flowerRepository,c,3,"迷你向日葵","小巧可爱的迷你向日葵",29.90,"/images/sunflower-mini.svg","黄色","幸福与活力","OUT_OF_STOCK");
        saveFlower(flowerRepository,c,4,"蝴蝶兰","优雅蝴蝶兰，花型优美花期持久",129.90,"/images/orchid-phalaenopsis.svg","白紫","美丽与优雅");
        saveFlower(flowerRepository,c,4,"大花蕙兰","异域大花蕙兰，每枝多花绽放",149.90,"/images/orchid-cymbidium.svg","绿粉","美德与高尚");
        saveFlower(flowerRepository,c,5,"浪漫花束","红玫瑰配白百合与满天星",199.90,"/images/bouquet-romance.svg","混色","浪漫与爱情");
        saveFlower(flowerRepository,c,5,"阳光花束","黄郁金香与向日葵组合",169.90,"/images/bouquet-sunshine.svg","黄绿","欢乐与友谊");
        saveFlower(flowerRepository,c,5,"优雅花束","白玫瑰百合与兰花的优雅花束",249.90,"/images/bouquet-elegance.svg","白色","优雅与纯洁","LOW_STOCK");
        saveFlower(flowerRepository,c,6,"康乃馨","温馨康乃馨，传递爱与感恩",39.90,"/images/flower-carnation.svg","粉色","母爱与感恩");
        saveFlower(flowerRepository,c,7,"绣球花","饱满绣球花，色彩缤纷花团锦簇",79.90,"/images/flower-hydrangea.svg","蓝色","团圆美满");
        saveFlower(flowerRepository,c,7,"牡丹","国色天香的牡丹，花中之王",89.90,"/images/flower-peony.svg","粉色","富贵吉祥");
        saveFlower(flowerRepository,c,7,"薰衣草","芳香薰衣草，放松身心宁静致远",35.90,"/images/flower-lavender.svg","紫色","宁静与浪漫");
        saveFlower(flowerRepository,c,7,"雏菊","清新小雏菊，天真烂漫充满活力",25.90,"/images/flower-daisy.svg","白色","天真与希望");
        saveFlower(flowerRepository,c,7,"荷花","出淤泥而不染的荷花，清雅高洁",69.90,"/images/flower-lotus.svg","粉色","高洁与清廉");
    }
    private void saveFlower(FlowerRepository fr, Category[] c, int ci, String n, String d, double p, String img, String col, String lang, String stock){
        Flower f=new Flower();f.setName(n);f.setDescription(d);f.setPrice(BigDecimal.valueOf(p));f.setImageUrl(img);f.setCategory(c[ci]);f.setColor(col);f.setLanguage(lang);f.setStockStatus(stock);fr.save(f);
    }
    private void saveFlower(FlowerRepository fr, Category[] c, int ci, String n, String d, double p, String img, String col, String lang){
        saveFlower(fr,c,ci,n,d,p,img,col,lang,"IN_STOCK");
    }
}