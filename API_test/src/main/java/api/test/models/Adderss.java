package api.test.models;

import java.util.Objects;

public class Adderss {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geo geo;

    public Adderss() {}

    public Adderss(String street, String suite, String city, String zipcode, Geo geo) {
        this.street = street;
        this.suite = suite;
        this.city = city;
        this.zipcode = zipcode;
        this.geo = geo;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public Geo getGeo() {
        return geo;
    }

    public void setGeo(Geo geo) {
        this.geo = geo;
    }

    @Override
    public String toString() {
        return "Adderss{" +
                "street='" + street + '\'' +
                ", suite='" + suite + '\'' +
                ", city='" + city + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", geo=" + geo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Adderss adderss = (Adderss) o;
        return Objects.equals(street, adderss.street) && Objects.equals(suite, adderss.suite) && Objects.equals(city, adderss.city) && Objects.equals(zipcode, adderss.zipcode) && Objects.equals(geo, adderss.geo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, suite, city, zipcode, geo);
    }

    public static class Geo {
        private double lat;
        public double lng;

        public Geo() {}

        public Geo(double lat, double lng) {
            this.lat = lat;
            this.lng = lng;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLng() {
            return lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }

        @Override
        public String toString() {
            return "Geo{" +
                    "lat=" + lat +
                    ", lng=" + lng +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Geo geo = (Geo) o;
            return Double.compare(lat, geo.lat) == 0 && Double.compare(lng, geo.lng) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(lat, lng);
        }
    }
}
