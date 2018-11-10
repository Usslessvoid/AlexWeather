package com.example.drcreeper.alexweather.utils;

import com.example.drcreeper.alexweather.R;

public enum PicturePicker {
    w01d {
        @Override
        public int getPicture() {
            return R.drawable.w01d;
        }
    },
    w01n {
        @Override
        public int getPicture() {
            return R.drawable.w01n;
        }
    },
    w02d {
        @Override
        public int getPicture() {
            return R.drawable.w02d;
        }
    },
    w02n {
        @Override
        public int getPicture() {
            return R.drawable.w02n;
        }
    },
    w03d {
        @Override
        public int getPicture() {
            return R.drawable.w03d;
        }
    },
    w03n {
        @Override
        public int getPicture() {
            return R.drawable.w03n;
        }
    },
    w04d {
        @Override
        public int getPicture() {
            return R.drawable.w04d;
        }
    },
    w04n {
        @Override
        public int getPicture() {
            return R.drawable.w04n;
        }
    },
    w09d {
        @Override
        public int getPicture() {
            return R.drawable.w09d;
        }
    },
    w09n {
        @Override
        public int getPicture() {
            return R.drawable.w09n;
        }
    },
    w10d {
        @Override
        public int getPicture() {
            return R.drawable.w10d;
        }
    },
    w10n {
        @Override
        public int getPicture() {
            return R.drawable.w10n;
        }
    },
    w11d {
        @Override
        public int getPicture() {
            return R.drawable.w11d;
        }
    },
    w11n {
        @Override
        public int getPicture() {
            return R.drawable.w11n;
        }
    },
    w13d {
        @Override
        public int getPicture() {
            return R.drawable.w13d;
        }
    },
    w13n {
        @Override
        public int getPicture() {
            return R.drawable.w13n;
        }
    },
    w50d {
        @Override
        public int getPicture() {
            return R.drawable.w50d;
        }
    },
    w50n {
        @Override
        public int getPicture() {
            return R.drawable.w50n;
        }
    };


    public abstract int getPicture();
    public static  int getPicture(String name){
        int ico;
        try {
            PicturePicker picker = PicturePicker.valueOf("w" + name);
            ico = picker.getPicture();
        }catch (Exception e) {
            ico = R.drawable.none;
        }
        return ico;
    }
}
