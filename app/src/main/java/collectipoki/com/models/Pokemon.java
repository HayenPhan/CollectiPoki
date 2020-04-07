package collectipoki.com.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;

// Parcelable is just a way to pack java objects, custom java classes. So that they can be added to bundles or passed to Activities or bundles.

public class Pokemon implements Parcelable {
    private String name;
    private String height;
    private String weight;
    private String[] type;
    private String candy;
    private String spawn_time;
    private String img;

    // Constructor
    public Pokemon(String name, String height, String weight, String[] type, String candy, String spawn_time, String img) {
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.type = type;
        this.candy = candy;
        this.spawn_time = spawn_time;
        this.img = img;
    }

    // Empty constructor
    public Pokemon() {
    }

    // Parcelable implementation

    protected Pokemon(Parcel in) {
        name = in.readString();
        height = in.readString();
        weight = in.readString();
        type = in.createStringArray();
        candy = in.readString();
        spawn_time = in.readString();
        img = in.readString();
    }

    public static final Creator<Pokemon> CREATOR = new Creator<Pokemon>() {
        @Override
        public Pokemon createFromParcel(Parcel in) {
            return new Pokemon(in);
        }

        @Override
        public Pokemon[] newArray(int size) {
            return new Pokemon[size];
        }
    };

    // Getter and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String[] getType() {
        return type;
    }

    public void setType(String[] type) {
        this.type = type;
    }

    public String getCandy() {
        return candy;
    }

    public void setCandy(String candy) {
        this.candy = candy;
    }

    public String getSpawn_time() {
        return spawn_time;
    }

    public void setSpawn_time(String spawn_time) {
        this.spawn_time = spawn_time;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    // Print out all values to the fields (toString() method)
    @Override
    public String toString() {
        return "Pokemon{" +
                "name='" + name + '\'' +
                ", height='" + height + '\'' +
                ", weight='" + weight + '\'' +
                ", type=" + Arrays.toString(type) +
                ", candy='" + candy + '\'' +
                ", spawn_time='" + spawn_time + '\'' +
                ", img='" + img + '\'' +
                '}';
    }


    // Needed to implement this for Parcelable
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(height);
        dest.writeString(weight);
        dest.writeStringArray(type);
        dest.writeString(candy);
        dest.writeString(spawn_time);
        dest.writeString(img);
    }
    //
}
