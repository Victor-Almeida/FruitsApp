package victor.fruitsapp;

import android.content.Context;
import android.support.constraint.ConstraintHelper;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FruitAdapter extends ArrayAdapter {
    List fruits = new ArrayList();

    public FruitAdapter(Context context, int resource) {
        super(context, resource);
    }


    public void add(Fruit fruit){
        super.add(fruit);
        fruits.add(fruit);
    }

    @Override
    public int getCount(){
        return fruits.size();
    }

    @Override
    public Object getItem(int position){
        return fruits.get(position);
    }

    public Fruit getFruit(int position){
        Object obj = fruits.get(position);
        Fruit fruit = (Fruit) obj;
        return fruit;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View list_item_layout;
        list_item_layout = convertView;
        FruitHolder fruitHolder;

        if(list_item_layout == null){
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            list_item_layout = layoutInflater.inflate(R.layout.list_item_layout, parent, false);
            fruitHolder = new FruitHolder();
            fruitHolder.header = list_item_layout.findViewById(R.id.headerTextView);
            fruitHolder.footer = list_item_layout.findViewById(R.id.footerTextView);
          //  fruitHolder.button = list_item_layout.findViewById(R.id.moreInfoButton);
            list_item_layout.setTag(fruitHolder);
        }else
            fruitHolder = (FruitHolder)list_item_layout.getTag();

        Fruit fruit = (Fruit) this.getItem(position);
        fruitHolder.header.setText(fruit.getFruit());
        fruitHolder.footer.setText(String.format(java.util.Locale.US, "US$%.2f", fruit.getPrice()));

        return list_item_layout;
    }

    static class FruitHolder {
        TextView header, footer, button;
    }
}


