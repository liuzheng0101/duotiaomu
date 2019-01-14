package com.example.duotiaomu.bean;

import java.util.List;

public class UserInfo {
    public String msg;
    public String code;
    public Data data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data{
        public List<Banner> banner;
        public List<Fenlei> fenlei;
        public Miao miaosha;
        public Tui tuijian;

        public List<Banner> getBanner() {
            return banner;
        }

        public void setBanner(List<Banner> banner) {
            this.banner = banner;
        }

        public List<Fenlei> getFenlei() {
            return fenlei;
        }

        public void setFenlei(List<Fenlei> fenlei) {
            this.fenlei = fenlei;
        }

        public Miao getMiaosha() {
            return miaosha;
        }

        public void setMiaosha(Miao miaosha) {
            this.miaosha = miaosha;
        }

        public Tui getTuijian() {
            return tuijian;
        }

        public void setTuijian(Tui tuijian) {
            this.tuijian = tuijian;
        }

        public class Banner{
            public String icon;

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }
        }
        public class Fenlei{
            public String name;
            public String icon;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }
        }
        public class Miao{
            public List<Li> list;
            public String name;
            public int time;

            public List<Li> getList() {
                return list;
            }

            public void setList(List<Li> list) {
                this.list = list;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getTime() {
                return time;
            }

            public void setTime(int time) {
                this.time = time;
            }

            public class Li{
                public String images;
                public String price;
                public String bargainPrice;

                public String getImages() {
                    return images;
                }

                public void setImages(String images) {
                    this.images = images;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public String getBargainPrice() {
                    return bargainPrice;
                }

                public void setBargainPrice(String bargainPrice) {
                    this.bargainPrice = bargainPrice;
                }
            }
        }
        public class Tui{
            public String name;
            public List<LL> list;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<LL> getList() {
                return list;
            }

            public void setList(List<LL> list) {
                this.list = list;
            }

            public class LL{
                public String title;
                public String images;
                public String price;

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getImages() {
                    return images;
                }

                public void setImages(String images) {
                    this.images = images;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }
            }
        }
    }
}
