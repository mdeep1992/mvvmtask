package com.example.mvvmtask.View;

import static kotlinx.coroutines.flow.FlowKt.collect;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvvmtask.Adapter.CategoryAdapter;
import com.example.mvvmtask.Adapter.CategoryitemAdapter;
import com.example.mvvmtask.Adapter.ListAdapter;
import com.example.mvvmtask.Interface.CategoryListlistener;
import com.example.mvvmtask.Interface.CategoryitemListener;
import com.example.mvvmtask.Model.CategoryItemList.CategoryItemListModel;
import com.example.mvvmtask.Model.CategoryItemList.Item;
import com.example.mvvmtask.Model.CategoryList.Category;
import com.example.mvvmtask.Model.CategoryList.CategorylistModel;
import com.example.mvvmtask.Model.list.Datum;
import com.example.mvvmtask.Model.list.ListModel;
import com.example.mvvmtask.R;
import com.example.mvvmtask.ViewModel.ListViewModel;
import com.example.mvvmtask.ViewModel.RecyclerViewModel;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class RecyclerActivity extends AppCompatActivity {
    RecyclerView categorylistrecycler, categoryitem_recycler;
    CategoryAdapter catlistadapter;
    CategoryitemAdapter categoryitemAdapter;
    ProgressBar progressBar;
    RecyclerViewModel recyclerViewModel;
    ArrayList<Category> categorylist = new ArrayList<>();
    ArrayList<Item> catitemlist = new ArrayList<>();
    Button one, two, three, four, five, six, seven, eight, nine, zero, Dot, doublezero;
    TextView txt_item_total_amount, txt_items_rate_selected, txt_items_rate_row;
    AppCompatTextView total_item_selection_count, selected_item_qty, selected_item_rate;
    int oldposition = -1;
    Boolean catSelectNew = false;
    ArrayList<Item> sale_item_list = null;
    ArrayList<Item> selected_item_list = new ArrayList<>();
    Boolean firstclick = true;
    Boolean dotselected = false;
    Double old_kept_qty = 0.0;
    Boolean isnewselected = false;
    Double current_selected_item_amount = 0.0;
    Item current_selected_item;
    String totalAmont = "";
    ArrayList<Integer>  keep_item_list_position=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        doInitContent();
        doInitViewmodel();
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dotselected){
                    doQTYCountForSumOfRate(1.0,false,false);
                }else{
                    doQTYCountForSumOfRate(0.1,false,false);
                }
            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dotselected){
                    doQTYCountForSumOfRate(2.0,false,false);
                }else{
                    doQTYCountForSumOfRate(0.2,false,false);
                }
            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dotselected){
                    doQTYCountForSumOfRate(3.0,false,false);
                }else{
                    doQTYCountForSumOfRate(0.3,false,false);
                }
            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dotselected){
                    doQTYCountForSumOfRate(4.0,false,false);
                }else{
                    doQTYCountForSumOfRate(0.4,false,false);
                }
            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dotselected){
                    doQTYCountForSumOfRate(5.0,false,false);
                }else{
                    doQTYCountForSumOfRate(0.5,false,false);
                }
            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dotselected){
                    doQTYCountForSumOfRate(6.0,false,false);
                }else{
                    doQTYCountForSumOfRate(0.6,false,false);
                }
            }
        });
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dotselected){
                    doQTYCountForSumOfRate(7.0,false,false);
                }else{
                    doQTYCountForSumOfRate(0.7,false,false);
                }
            }
        });
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dotselected){
                    doQTYCountForSumOfRate(8.0,false,false);
                }else{
                    doQTYCountForSumOfRate(0.8,false,false);
                }
            }
        });
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dotselected){
                    doQTYCountForSumOfRate(9.0,false,false);
                }else{
                    doQTYCountForSumOfRate(0.9,false,false);
                }
            }
        });
        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    doQTYCountForSumOfRate(0.0,false,false);
            }
        });
        doublezero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doQTYCountForSumOfRate(0.0,true,false);
            }
        });
        Dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doQTYCountForSumOfRate(0.0,false,true);
            }
        });
    }

    private void doInitContent() {
        categorylistrecycler = findViewById(R.id.category);
        categoryitem_recycler = findViewById(R.id.recycler_item_view);
        progressBar = findViewById(R.id.progressBar);
        one = findViewById(R.id.btn_cal_one);
        two = findViewById(R.id.btn_cal_second);
        three = findViewById(R.id.btn_cal_third);
        four = findViewById(R.id.btn_cal_four);
        five = findViewById(R.id.btn_cal_five);
        six = findViewById(R.id.btn_cal_six);
        seven = findViewById(R.id.btn_cal_seven);
        eight = findViewById(R.id.btn_cal_eight);
        nine = findViewById(R.id.btn_cal_nine);
        Dot = findViewById(R.id.btn_cal_dot);
        zero = findViewById(R.id.btn_cal_zero);
        doublezero = findViewById(R.id.btn_cal_double_zero);
        txt_item_total_amount = findViewById(R.id.txt_item_total_amount);
        txt_items_rate_selected = findViewById(R.id.txt_items_rate_selected);
        txt_items_rate_row = findViewById(R.id.txt_items_rate_row);
        total_item_selection_count = findViewById(R.id.total_item_selection_count);
        selected_item_qty = findViewById(R.id.selected_item_qty);
        selected_item_rate = findViewById(R.id.selected_item_rate);
    }




    private void doInitViewmodel() {
        recyclerViewModel = new ViewModelProvider(this).get(RecyclerViewModel.class);
        recyclerViewModel.getcategorylist();
        recyclerViewModel.catitemlist.observe(this, new Observer<CategoryItemListModel>() {
            @Override
            public void onChanged(CategoryItemListModel categoryItemListModel) {
                final LinearLayoutManager[] manager = {new GridLayoutManager(getApplicationContext(), 3, RecyclerView.VERTICAL, false)};
                categoryitem_recycler.setHasFixedSize(true);
                categoryitem_recycler.setLayoutManager(manager[0]);
                catitemlist.clear();
                catitemlist.addAll(categoryItemListModel.getItems());
                categoryitemAdapter = new CategoryitemAdapter(catitemlist, getApplicationContext(), new CategoryitemListener() {

                    @Override
                    public void OnClick(Item item, int position, Boolean isselect) {
                         catSelectNew = false;
                        if (selected_item_list.isEmpty()) {
                            oldposition = -1;
                        }
                        if (!isselect) {
                            addNewitem(item, position, isselect);
                        } else if (isselect) {
                            if (oldposition != -1) {
                                if (sale_item_list.get(oldposition).getQtys() == 0.0) {
                                    Toast.makeText(getApplicationContext(), "Please enter the Quantity for selected item", Toast.LENGTH_SHORT).show();
                                } else {
                                    addNewitem(item, position, isselect);
                                }
                            } else {
                                addNewitem(item, position, isselect);
                            }
                        } else {
                            addNewitem(item, position, isselect);
                        }
                    }

                    private void addNewitem(Item item, int position, Boolean isselect) {
                        catitemlist.get(position).setSelect(isselect);
                        categoryitemAdapter.notifyDataSetChanged();
                        doCallbeforeSelectNewtext();
                        firstclick = true;
                        dotselected = false;
                        if (item != null) {
                            applyLogic(catitemlist, item, position, isselect);
                        }
                    }
                });

                categoryitem_recycler.setAdapter(categoryitemAdapter);
                categoryitemAdapter.notifyDataSetChanged();
            }


        });


        recyclerViewModel.catlist.observe(this, new Observer<CategorylistModel>() {
            @Override
            public void onChanged(CategorylistModel category) {
                final LinearLayoutManager[] manager = {new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false)};
                categorylistrecycler.setHasFixedSize(true);
                categorylistrecycler.setLayoutManager(manager[0]);
                categorylist.clear();
                categorylist.addAll(category.getCategory());
                recyclerViewModel.getcategoryitem(0, false);
                catlistadapter = new CategoryAdapter(getApplicationContext(), categorylist, new CategoryListlistener() {
                    @Override
                    public void Itemclicked(int id) {

                        recyclerViewModel.getcategoryitem(id, true);
                    }
                });
                categorylistrecycler.setAdapter(catlistadapter);
                catlistadapter.notifyDataSetChanged();
            }

        });


    }

    @SuppressLint("SetTextI18n")
    private void applyLogic(ArrayList<Item> catitemlist, Item item, int position, Boolean isselect) {

        oldposition = position;
        int count = 0;

        if (selected_item_list.size() > 0) {
            if (!isselect) {
                count = selected_item_list.size() - 1;
            } else {
                count = selected_item_list.size() + 1;
            }
        } else if (isselect) {
            count = 1;
        }

        Log.e("sel_count", String.valueOf(count));

        if (count == 0) {
            total_item_selection_count.setText("No Items Selected.");
            selected_item_rate.setText("");
            selected_item_qty.setText("");
            txt_item_total_amount.setText("");
            txt_items_rate_selected.setText("");
            totalAmont = "";
            /*  selected_item_list.clear();
            keep_item_list_position.clear();*/
        } else {
            selected_item_qty.setText("QTY:" + "0");

            if(isselect){
                selected_item_rate.setText("Rate:"+roundOff(item.getRate().toString()));
            }else{
                selected_item_rate.setText("Rate:"+roundOff(item.getRate().toString()));
            }
            total_item_selection_count.setText("Totally" +count+"items selected.");

        }
        if (isselect) {
            old_kept_qty = 1.0;
            isnewselected= true;
            item.setQtys( 0.0);
            Double amount = (item.getRate() * 0);
            //            float amount = (float) (item.getRate() * 1);
            item.setTotal_amt(amount);
            if (selected_item_list.size() == 0) {
                item.setSelected_position(0);

                keep_item_list_position.add(0);
                txt_item_total_amount.setText(  "Total RS:"+ roundOff(amount.toString()));
                totalAmont = amount.toString();
            } else {
                if (keep_item_list_position != null && !keep_item_list_position.isEmpty()) {
                    int last_list_position = keep_item_list_position.get(keep_item_list_position.size() - 1);
                    Log.e("keep_position", String.valueOf(last_list_position));
                    item.setSelected_position(last_list_position + 1);
                    keep_item_list_position.add(last_list_position + 1);
                }
            }
            selected_item_list.add(item);
            doSetFirstSelectedItemRate(amount);
            current_selected_item = DoReturnSelectedItem(item);
        } else {
            old_kept_qty = 0.0;
            isnewselected = false;
            int my_index= selected_item_list.indexOf(item.getId());
            selected_item_list.remove(my_index);
            keep_item_list_position.remove(my_index);
            DoUpdateSelectedTotalItemAmount();
            doCallbeforeSelectNewtext();
            txt_items_rate_row.setText("");
            oldposition = -1;
        }


    }
    private void DoUpdateSelectedTotalItemAmount() {
        if (selected_item_list.size() > 0) {
            Double sum_amount = sum_total_item_rate(selected_item_list);
            txt_item_total_amount.setText( "Total RS: "+roundOff(sum_amount.toString()));
            totalAmont = sum_amount.toString();
        }
    }
    private Item DoReturnSelectedItem( Item item) {
        return item;
    }
    private void  doSetFirstSelectedItemRate( Double amount) {
        if (selected_item_list.size() == 1) {

            String amounStr = "";
            String []zero = amount.toString().split(".");
            if (zero .equals("0") ) {
                amounStr = amount.toString().replace(".0", "");
            } else {
                amounStr = amount.toString();
            }
            txt_items_rate_row.setText(amounStr);


//            Log.e("line_492", sales_item_list_binding.txtItemsRateSelected.text.toString())

        } else {
            String amounStr = "";
            String []   zero = amount.toString().split(".");
            if (zero .equals("0") ) {
                amounStr = amount.toString().replace(".0", "");
            } else {
                amounStr = amount.toString();
            }
            txt_items_rate_row.setText(amounStr);
//            Log.e("line_507", sales_item_list_binding.txtItemsRateSelected.text.toString())

        }
        if (selected_item_list.size() == 1) {
            txt_item_total_amount.setText(  "Total RS:"+ roundOff(amount.toString()));

            totalAmont = amount.toString();
        } else {
            Double sum_amount = sum_total_item_rate(selected_item_list);
            txt_item_total_amount.setText(   "Total RS: "+roundOff(sum_amount.toString()));
            totalAmont = sum_amount.toString();
        }
    }

    private void doCallbeforeSelectNewtext() {
        if(selected_item_list.size() > 0)
        {
            String selected_total_amount = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                selected_total_amount = selected_item_list
                        .stream()
                        .map(a -> String.valueOf(a.getTotal_amt()))
                        .collect(Collectors.joining("+"));
            }

            txt_items_rate_selected.setText(selected_total_amount);


        }
    }

    private void doQTYCountForSumOfRate(
            Double qty,
            Boolean doubezero,
            Boolean dot
    ) {

        if (catSelectNew) {
            Toast.makeText(this, "please select new one", Toast.LENGTH_SHORT).show();

        } else {
            if (String.valueOf(Math.round(old_kept_qty)).length() < 3) {
                if (!dot) {
                    Double qty1 = qty;
                    if (firstclick) {
                        firstclick = false;
                    } else {
                        if (doubezero && !dotselected) {
                            Log.e("calculation_old", old_kept_qty.toString());
                            Log.e("calculation_new", qty.toString());
                            String[] strs = old_kept_qty.toString().split(".");
                            qty1 = Double.valueOf((strs[0] + "00"));
                            qty1 = Double.valueOf(qty1 + ("." + strs[1]));

                        } else if (dotselected) {
                            String[] strs2 = old_kept_qty.toString().split(".");
                            if (strs2[1].length() < 2) {
                                Log.e("calculation_new", strs2[0]);
                                Log.e("calculation_new", strs2[1]);
                                String dotqty = "";
                                if (strs2[1] != "0") {
                                    dotqty = strs2[1] + qty.toString().replace("0.", "");
                                } else {
                                    dotqty = qty.toString().replace("0.", "");
                                }

                                Log.e("dotqty", dotqty);
                                Log.e("qty", String.valueOf(qty));
                                qty = Double.valueOf(strs2[0]) + Double.valueOf("0." + dotqty);

                            } else {
                                qty = old_kept_qty;
                                Log.e("qty2", String.valueOf(qty));
                            }

                        } else if (dot) {
                            dotselected = true;


                        } else {
                            qty = Double.valueOf((old_kept_qty.toString().replace(".0", "") + Double.valueOf(qty.toString()
                                    .replace(".0", ""))));
                            Log.e("qty3", String.valueOf(qty));
                        }

                    }
                    if (isnewselected) {
                        old_kept_qty = 0 + qty;
                        Double rate = selected_item_list.get(selected_item_list.size() - 1).getRate();
                        current_selected_item_amount = roundOffDecimal(rate * old_kept_qty);
                        selected_item_qty.setText("QTY: " + roundOff(old_kept_qty.toString()));
                        current_selected_item.setTotal_amt(current_selected_item_amount);
                        current_selected_item.setQtys(old_kept_qty);
                        String[] zero = current_selected_item_amount.toString().split(".");
                        String amounStr = "";
                        if (zero .equals("0") ) {
                            amounStr = current_selected_item_amount.toString().replace(".0", "");
                        } else {
                            amounStr = current_selected_item_amount.toString();
                        }

                        txt_items_rate_row.setText(amounStr);

                        Log.e("txt_items_rate_selected", txt_items_rate_selected.toString());

                        if (selected_item_list.size() == 1) {
                            txt_item_total_amount.setText("Total RS:"+ roundOff(current_selected_item_amount.toString()));

                            totalAmont = current_selected_item_amount.toString();
                        } else {
                            Double sum_amount = sum_total_item_rate(selected_item_list);
                            txt_item_total_amount.setText("Total RS: "+roundOff(sum_amount.toString()));

                            totalAmont = sum_amount.toString();
                        }
                    } else {
                        Toast.makeText(this, "Please select a new one", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    dotselected = true;
                }

            }
        }

    }


    private Double sum_total_item_rate(List<Item> list) {
        Double sum = 0.0;
        for (Item i : list) {
            sum += i.getTotal_amt();
        }
        return sum;
    }

    private Double roundOffDecimal(Double number) {

        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.FLOOR);
        return Double.valueOf(df.format(number));
    }

    private String roundOff(String price) {
        String amount = "";
        String[] zero = price.split(".");
        if (zero.equals("0")) {
            amount = price.replace(".0", "");
        } else {
            amount = price;
        }
        return amount;
    }
}