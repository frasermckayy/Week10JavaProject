package db;

import models.items.Clothe;
import models.items.Electronic;
import models.items.Food;
import models.items.Item;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class DBStock {

    private static Session session;

    public static List<Food> getFoodStock(){
        List<Food> foodStock = DBHelper.getAll(Food.class);
        foodStock.removeIf(Food -> Food.getBasket() != null );
        return foodStock;
    }

    public static List<Clothe> getClotheStock(){
        List<Clothe> clotheStock = DBHelper.getAll(Clothe.class);
        clotheStock.removeIf(Clothe -> Clothe.getBasket() != null);
        return clotheStock;
    }

    public static List<Electronic> getElectronicStock(){
        List<Electronic> electronicStock = DBHelper.getAll(Electronic.class);
        electronicStock.removeIf(Electronic -> Electronic.getBasket() != null);
        return electronicStock;
    }

    public static void returnStock(Item item){

        if (item.prettyCategory().equals("food")){
            List<Food> foodList = getFoodStock();
            Food tempFood = (Food) item;
            for (Food food : foodList){
                if (tempFood.getName().equals(food.getName())){
                    food.setQuantity(food.getQuantity() + item.getQuantity());
                    DBHelper.save(food);
                }
            }

        } else if (item.prettyCategory().equals("clothe")){
            List<Clothe> clotheList = getClotheStock();
            Clothe tempClothe = (Clothe) item;
            for (Clothe clothe : clotheList){
                if ((clothe.getColor().equals(tempClothe.getColor()))  && (clothe.getSize() == tempClothe.getSize())){
                    clothe.setQuantity(clothe.getQuantity() + item.getQuantity());
                    DBHelper.save(clothe);
                }
            }

        } else if (item.prettyCategory().equals("electronic")){
            List<Electronic> electronicList = getElectronicStock();
            Electronic tempElectronic = (Electronic) item;
            for (Electronic electronic : electronicList){
                if (electronic.getParts().equals(tempElectronic.getParts())){
                    electronic.setQuantity(electronic.getQuantity() + item.getQuantity());
                }
            }
        }

    }

}

