package com.fireshot.contractprinter.view;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.GridView;

import com.fireshot.contractprinter.R;
import com.fireshot.contractprinter.adapters.GvTemplatesAdapter;
import com.fireshot.contractprinter.models.Template;
import com.fireshot.contractprinter.print.Printer;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.OnItemSelected;

public class MenuActivity extends AppCompatActivity {

    @Bind(R.id.gv_templates)
    protected GridView mGvTemplates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        loadTemplates();
    }

    private void loadTemplates(){
        Template[] templates = {
                new Template("Template", "com/fireshot/contractprinter/pdfs/template.pdf"),
                new Template("Template", "com/fireshot/contractprinter/pdfs/template.pdf"),
                new Template("Template", "com/fireshot/contractprinter/pdfs/template.pdf"),
                new Template("Template", "com/fireshot/contractprinter/pdfs/template.pdf"),
                new Template("Template", "com/fireshot/contractprinter/pdfs/template.pdf"),
                new Template("Template", "com/fireshot/contractprinter/pdfs/template.pdf"),
                new Template("Template", "com/fireshot/contractprinter/pdfs/template.pdf"),
                new Template("Template", "com/fireshot/contractprinter/pdfs/template.pdf"),
                new Template("Template", "com/fireshot/contractprinter/pdfs/template.pdf"),
                new Template("Template", "com/fireshot/contractprinter/pdfs/template.pdf")
        };

        mGvTemplates.setAdapter(new GvTemplatesAdapter(templates, this));
    }


    @OnItemClick(R.id.gv_templates)
    public void print(int pos){
        String title = ((Template) mGvTemplates.getAdapter().getItem(pos)).getTitle();
        String path = ((Template) mGvTemplates.getAdapter().getItem(pos)).getPath();
        Printer p = new Printer(this, 1, path);
        p.execute();
    }
}
