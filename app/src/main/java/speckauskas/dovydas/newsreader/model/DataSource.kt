package speckauskas.dovydas.newsreader.model

class DataSource{

    companion object{

        fun createDataSet(): ArrayList<NewsPostModel>{
            val list = ArrayList<NewsPostModel>()
            list.add(
                NewsPostModel(
                    "https://cdn.arstechnica.net/wp-content/uploads/2019/11/oneAPI-heroes-760x380.png",
                    "Write AI code once, run anywhere—it's not Java, it's Intel's oneAPI - Ars Technica",
                    "2019-11-18T12:48:00Z",
                    "Jim Salter",
                    "OneAPI unifies code across multiple hardware targets—like Nvidia and Intel GPUs."
                )
            )
            list.add(
                NewsPostModel(
                    "https://i2.wp.com/media.boingboing.net/wp-content/uploads/2019/11/Untitled.jpeg?fit=800%2C600&ssl=1",
                    "Microsoft’s Cortana is sleeping with the fishes - Boing Boing",
                    "2019-11-18T12:00:00Z",
                    "",
                    "Lots of people are fine with allowing a helpful wiretap into their lives. I am not one of them. None of them. So, when I heard that Cortana was being sent out to pasture by Microsoft, I was pretty …"
                )
            )
            list.add(
                NewsPostModel(
                    "https://www.androidpolice.com/wp-content/uploads/2019/11/google-store-black-friday-deals.png",
                    "Google teases Black Friday deals: $200 off Pixel 4, $100 off Pixel 3a, up to $648 off Pixel Slate bundle - Android Police",
                    "2019-11-18T11:55:00Z",
                    "",
                    "With Black Friday less than two weeks away, retailers have already started discounting some items and teasing their full-blown deal extravaganza. Google"
                )
            )
            list.add(
                NewsPostModel(
                    "https://o.aolcdn.com/images/dims?thumbnail=1200%2C630&quality=80&image_uri=https%3A%2F%2Fo.aolcdn.com%2Fimages%2Fdims%3Fresize%3D2000%252C2000%252Cshrink%26image_uri%3Dhttps%253A%252F%252Fs.yimg.com%252Fos%252Fcreatr-uploaded-images%252F2019-11%252F18a6a6b0-09ef-11ea-acff-99e50bc8ee00%26client%3Da1acac3e1b3290917d92%26signature%3Dac3a5bdbd16b1f36b31a1163ee627889a98f6ffd&client=amp-blogside-v2&signature=3bbeb01ebb88755a7006ce60e5d81a188def6bd5",
                    "The Morning After: Google Stadia's launch game line-up gets a big boost - Engadget",
                    "2019-11-18T11:45:39Z",
                    "Mat Smith",
                    "Hey, good morning! We kick off the week with the launch of Google's much-publicized Stadia streaming game service tomorrow, with the company fleshing out the la..."
                )
            )
            list.add(
                NewsPostModel(
                    "https://cdn.vox-cdn.com/thumbor/HvPGC6EzcF0dFlORaWusBkc8YfQ=/0x427:3768x2400/fit-in/1200x630/cdn.vox-cdn.com/uploads/chorus_asset/file/19386132/IMG_20191015_180254__1_.jpg",
                    "Logitech’s Adaptive Gaming Kit is a cheaper way in to accessible gaming - The Verge",
                    "2019-11-18T11:43:46Z",
                    "Jon Porter",
                    "Logitech’s new Adaptive Gaming Kit is a collection of 12 buttons and triggers that are designed to work with the Xbox Adaptive Controller. The buttons plug into the controller’s 3.5mm jacks and the triggers plug in via USB."
                )
            )
            return list
        }
    }
}