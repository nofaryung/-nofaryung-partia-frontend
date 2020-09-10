package com.example.partia;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
        mv = {1, 1, 15},
        bv = {1, 0, 3},
        k = 1,
        d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ\u0006\u0010\u000e\u001a\u00020\u000bJ\u0010\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0006\u0010\u0012\u001a\u00020\u000bJ\u0012\u0010\u0013\u001a\u00020\u000b2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0016"},
        d2 = {"Lcom/example/partia/ActivityStatistics;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "chart", "Lcom/github/mikephil/charting/charts/PieChart;", "decrease", "Landroid/widget/Button;", "increase", "spinnerValue", "Landroid/widget/TextView;", "add_btn_clicked", "", "view", "Landroid/view/View;", "decreaseInteger", "display", "number", "", "increaseInteger", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "app"}
)
public final class ActivityStatistics extends AppCompatActivity {
    private PieChart chart;
    private Button increase;
    private Button decrease;
    private TextView spinnerValue;
    private HashMap _$_findViewCache;

    @SuppressLint("ResourceType")
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(-1300020);
        View var10001 = this.findViewById(-1000055);
        Intrinsics.checkExpressionValueIsNotNull(var10001, "findViewById(R.id.pieChart)");
        this.chart = (PieChart)var10001;
        var10001 = this.findViewById(-1000026);
        Intrinsics.checkExpressionValueIsNotNull(var10001, "findViewById(R.id.button_Inc)");
        this.increase = (Button)var10001;
        var10001 = this.findViewById(-1000017);
        Intrinsics.checkExpressionValueIsNotNull(var10001, "findViewById(R.id.button_Dec)");
        this.decrease = (Button)var10001;
        var10001 = this.findViewById(-1000002);
        Intrinsics.checkExpressionValueIsNotNull(var10001, "findViewById(R.id.textview_SpinnerValue)");
        this.spinnerValue = (TextView)var10001;
        PieChart var10000 = this.chart;
        if (var10000 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("chart");
        }

        var10000.setUsePercentValues(true);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new PieEntry(40.0F));
        arrayList.add(new PieEntry(35.0F));
        arrayList.add(new PieEntry(25.0F));
        PieDataSet pieDataSet = new PieDataSet((List)arrayList, "Meal Type");
        var10000 = this.chart;
        if (var10000 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("chart");
        }

        var10000.setTransparentCircleColor(-1);
        PieData pieData = new PieData((IPieDataSet)pieDataSet);
        var10000 = this.chart;
        if (var10000 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("chart");
        }

        var10000.setData((PieData) pieData);
        Button var5 = this.increase;
        if (var5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("increase");
        }

        var5.setOnClickListener((OnClickListener)(new OnClickListener() {
            public final void onClick(View it) {
                ActivityStatistics.this.increaseInteger();
            }
        }));
        var5 = this.decrease;
        if (var5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("decrease");
        }

        var5.setOnClickListener((OnClickListener)(new OnClickListener() {
            public final void onClick(View it) {
                ActivityStatistics.this.decreaseInteger();
            }
        }));
    }

    public final void increaseInteger() {
        TextView var10001 = this.spinnerValue;
        if (var10001 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("spinnerValue");
        }

        String var1 = var10001.getText().toString();
        boolean var2 = false;
        int var4 = Integer.parseInt(var1);
        this.display(var4 + 1);
    }

    public final void decreaseInteger() {
        TextView var10001 = this.spinnerValue;
        if (var10001 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("spinnerValue");
        }

        String var1 = var10001.getText().toString();
        boolean var2 = false;
        int var4 = Integer.parseInt(var1);
        this.display(var4 - 1);
    }

    private final void display(int number) {
        TextView var10000 = this.spinnerValue;
        if (var10000 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("spinnerValue");
        }

        var10000.setText((CharSequence)String.valueOf(number));
    }

    public final void add_btn_clicked(@NotNull View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
    }

    public View _$_findCachedViewById(int var1) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }

        View var2 = (View)this._$_findViewCache.get(var1);
        if (var2 == null) {
            var2 = this.findViewById(var1);
            this._$_findViewCache.put(var1, var2);
        }

        return var2;
    }

    public void _$_clearFindViewByIdCache() {
        if (this._$_findViewCache != null) {
            this._$_findViewCache.clear();
        }

    }
}
