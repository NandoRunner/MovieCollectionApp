package fandradetecinfo.com.moviecollectionapp.Models;

public class Tela {

    private int list_row_bg;
    
    private String url_main;
    
    private String url_second;

    private boolean loaded;

    public boolean isLoaded() {
        return loaded;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    public int getList_row_bg() {
        return list_row_bg;
    }

    public void setList_row_bg(int list_row_bg) {
        this.list_row_bg = list_row_bg;
    }


    public String getUrl_main() {
        return url_main;
    }

    public void setUrl_main(String url_main) {
        this.url_main = url_main;
    }

    public String getUrl_second() {
        return url_second;
    }

    public void setUrl_second(String url_second) {
        this.url_second = url_second;
    }
}
