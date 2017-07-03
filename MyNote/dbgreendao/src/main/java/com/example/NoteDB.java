package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class NoteDB {
    public static void main(String[] args) throws Exception {
        //创建一个用于添加实体的模式对象
        Schema schema = new Schema(1,"com.h.mynote.greendao.greenBean");
        // master、session、dao生成目录
        schema.setDefaultJavaPackageTest("com.h.mynote.greendao.greenDao");
        schema.setDefaultJavaPackageDao("com.h.mynote.greendao.greenDao");
        schema.enableKeepSectionsByDefault();
        addNewsCate(schema);
        //最后我们将使用 DAOGenerator 类的 generateAll() 方法自动生成代码，此处你需要根据自己的情况更改输出目录
        new DaoGenerator().generateAll(schema,"../MyNote/app/src/main/java");
    }

    private static void addNewsCate(Schema schema){
        Entity entity = schema.addEntity("NewsCate");
        entity.addIdProperty();
        entity.addStringProperty("name");
        entity.addStringProperty("value");
        entity.addStringProperty("isSel");
    }
}
