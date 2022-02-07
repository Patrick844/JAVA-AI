package fr.epita.mnist.services;

import java.util.List;


import fr.epita.mnist.datamodel.MNISTImage;

public class MNISTImageProcessor {

    public MNISTImage computeCentroid(Double label, List<MNISTImage> images) {

        MNISTImage centroidMean = new MNISTImage(label, new double[28][28]);



        double size = images.size();
        // Compute Mean of each image with same label
        for (MNISTImage image : images) {

            for (int i = 0; i < image.getPixels().length; i++) {
                for (int j = 0; j < image.getPixels().length; j++) {

                centroidMean.getPixels()[i][j] = centroidMean.getPixels()[i][j] + (image.getPixels()[i][j] /(size));
                }
            }
        }




        return centroidMean;
    }

    public MNISTImage computeCentroidStd(Double label, List<MNISTImage> images) {

        MNISTImage centroid = new MNISTImage(label, new double[28][28]);
        MNISTImage centroidStd = new MNISTImage(label, new double[28][28]);


        // Compute Mean of each image with same label
        double size = images.size();
        for (MNISTImage image : images) {

            for (int i = 0; i < image.getPixels().length; i++) {
                for (int j = 0; j < image.getPixels().length; j++) {

                    centroid.getPixels()[i][j] = centroid.getPixels()[i][j] + (image.getPixels()[i][j] /(size));
                }
            }
        }


        // Compute Std of each image with same label
        for (MNISTImage image : images) {
            double[][] pixels = image.getPixels();
            for (int i = 0; i < pixels.length; i++) {
                for (int j = 0; j < pixels.length; j++) {

                    centroidStd.getPixels()[i][j] = centroidStd.getPixels()[i][j] + (Math.pow((pixels[i][j] -centroid.getPixels()[i][j]),2))/(size);
                }
            }
        }

        for (int i = 0; i < centroidStd.getPixels().length; i++) {
            for (int j = 0; j < centroidStd.getPixels().length; j++) {

                centroidStd.getPixels()[i][j] = Math.sqrt(centroidStd.getPixels()[i][j]);


            }
        }



        return centroidStd;
    }


    public double computeDistance(MNISTImage image1, MNISTImage image2){
        double distance = 0.0;

        double[][] image1Pixels = image1.getPixels();
        double[][] image2Pixels = image2.getPixels();

        for (int i = 0 ; i < MNISTReader.MAX_ROW; i++){
            for (int j = 0 ; j < MNISTReader.MAX_COL; j++){
                distance = distance + Math.pow(image2Pixels[i][j] - image1Pixels[i][j],2);
            }
        }

        distance=Math.sqrt(distance);
        return distance;
    }

    public double computeDistanceStd(MNISTImage image1, MNISTImage image2, MNISTImage image3){


        double[][] image1Pixels = image1.getPixels();
        double[][] image2Pixels = image2.getPixels();
        double[][] image3Pixels = image3.getPixels();

        double zscore = 0;





        for (int i = 0 ; i < image3Pixels.length; i++){
            for (int j = 0 ; j < image3Pixels.length; j++){



                // Compute ZScore  of each image with same label
               if(image3Pixels[i][j] >0) {
                   // Add all the zscore together to get a single value
                    zscore += (Math.abs(image1Pixels[i][j] - image2Pixels[i][j])) / image3Pixels[i][j];
                }
            }
        }






        return zscore;
    }

    public static void displayImage(MNISTImage image) {
        double[][] pixels = image.getPixels();
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[i].length; j++) {
                if (pixels[i][j] < 128) {
                    System.out.print("..");
                } else {
                    System.out.print("xx");
                }
            }
            System.out.println();
        }
    }
}
