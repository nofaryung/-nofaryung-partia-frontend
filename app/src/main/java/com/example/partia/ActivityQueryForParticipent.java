package com.example.partia;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
        mv = {1, 1, 15},
        bv = {1, 0, 3},
        k = 1,
        d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014J\u0012\u0010\u0015\u001a\u00020\u00122\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"},
        d2 = {"Lcom/example/partia/ActivityQueryForParticipent;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "btnNext", "Landroid/widget/Button;", "currPage", "", "rb1", "Landroid/widget/RadioButton;", "rb2", "rb3", "rg", "Landroid/widget/RadioGroup;", "textViewPageCount", "Landroid/widget/TextView;", "textViewQuery", "totalNumOfPages", "next_btn_clicked", "", "view", "Landroid/view/View;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "app"}
)
public final class ActivityQueryForParticipent extends AppCompatActivity {
    private TextView textViewPageCount;
    private TextView textViewQuery;
    private RadioGroup rg;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private Button btnNext;
    private int totalNumOfPages = 3;
    private int currPage = 1;
    private HashMap _$_findViewCache;

    @SuppressLint("ResourceType")
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(-1300019);
        View var10001 = this.findViewById(-1000183);
        Intrinsics.checkExpressionValueIsNotNull(var10001, "findViewById(R.id.textView_pageCount)");
        this.textViewPageCount = (TextView)var10001;
        var10001 = this.findViewById(-1000088);
        Intrinsics.checkExpressionValueIsNotNull(var10001, "findViewById(R.id.textView_Query)");
        this.textViewQuery = (TextView)var10001;
        var10001 = this.findViewById(-1000016);
        Intrinsics.checkExpressionValueIsNotNull(var10001, "findViewById(R.id.rg)");
        this.rg = (RadioGroup)var10001;
        var10001 = this.findViewById(-1000143);
        Intrinsics.checkExpressionValueIsNotNull(var10001, "findViewById(R.id.radio_button_op1)");
        this.rb1 = (RadioButton)var10001;
        var10001 = this.findViewById(-1000145);
        Intrinsics.checkExpressionValueIsNotNull(var10001, "findViewById(R.id.radio_button_op2)");
        this.rb2 = (RadioButton)var10001;
        var10001 = this.findViewById(-1000144);
        Intrinsics.checkExpressionValueIsNotNull(var10001, "findViewById(R.id.radio_button_op3)");
        this.rb3 = (RadioButton)var10001;
        var10001 = this.findViewById(-1000106);
        Intrinsics.checkExpressionValueIsNotNull(var10001, "findViewById(R.id.button_next)");
        this.btnNext = (Button)var10001;
    }

    public final void next_btn_clicked(@NotNull View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        RadioGroup var10000 = this.rg;
        if (var10000 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rg");
        }

        var10000.clearCheck();
        if (this.currPage < this.totalNumOfPages) {
            int var10001 = this.currPage++;
            TextView var3;
            RadioButton var4;
            if (this.currPage == 2) {
                var3 = this.textViewQuery;
                if (var3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("textViewQuery");
                }

                var3.setText((CharSequence)"Choose your glass");
                var4 = this.rb1;
                if (var4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rb1");
                }

                var4.setText((CharSequence)"Vodka");
                var4 = this.rb2;
                if (var4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rb2");
                }

                var4.setText((CharSequence)"Gin");
                var4 = this.rb3;
                if (var4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rb3");
                }

                var4.setText((CharSequence)"Wine");
            }

            if (this.currPage == 3) {
                var3 = this.textViewQuery;
                if (var3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("textViewQuery");
                }

                var3.setText((CharSequence)"Are you allergic to...");
                var4 = this.rb1;
                if (var4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rb1");
                }

                var4.setText((CharSequence)"Peanuts");
                var4 = this.rb2;
                if (var4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rb2");
                }

                var4.setText((CharSequence)"Dairy");
                var4 = this.rb3;
                if (var4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rb3");
                }

                var4.setText((CharSequence)"Something else");
                Button var5 = this.btnNext;
                if (var5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("btnNext");
                }

                var5.setText((CharSequence)"Finish");
            }
        } else if (this.currPage == this.totalNumOfPages) {
        }

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
