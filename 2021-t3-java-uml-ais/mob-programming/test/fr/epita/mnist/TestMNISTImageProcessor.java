package fr.epita.mnist;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import fr.epita.mnist.datamodel.MNISTImage;
import fr.epita.mnist.services.MNISTImageProcessor;
import fr.epita.mnist.services.MNISTReader;

public class TestMNISTImageProcessor {


    public static void main(String[] args) throws FileNotFoundException {

        // Initialize

        double[][] confusion = new double[10][10];
        double predict_label = -1;
        double total_guess=0;
        double correct_guesses=0;

        MNISTImageProcessor processor = new MNISTImageProcessor();
        MNISTReader reader = new MNISTReader();



        // Read Images
        List<MNISTImage> imagesTrain = reader.readImages(new File("mob-programming/mnist_train.csv"), 10000);
        List<MNISTImage> imagesTest = reader.readImages(new File("mob-programming/mnist_test.csv"), 10000);

        Map<Double, List<MNISTImage>> imagesTrainByLabel = imagesTrain.stream().collect(Collectors.groupingBy(MNISTImage::getLabel));

        Map<Double, MNISTImage> centroidMean = new LinkedHashMap<>();
        Map<Double, MNISTImage> centroidsStd = new LinkedHashMap<>();

        for (Map.Entry<Double, List<MNISTImage>> entry : imagesTrainByLabel.entrySet()){

            // Compute centroid for Mean and Std
            MNISTImage centroid1 = processor.computeCentroid(entry.getKey(), entry.getValue());
            MNISTImage centroid2 = processor.computeCentroidStd(entry.getKey(), entry.getValue());
            centroidMean.put(centroid1.getLabel(), centroid1);
            centroidsStd.put(centroid2.getLabel(), centroid2);
        }


        for (MNISTImage i:
            imagesTest ) {

            double distance=1000000000;

            for (Map.Entry<Double, MNISTImage> entryCentroids : centroidMean.entrySet()) {

                // Zscore value based on Std
                double newZscore = processor.computeDistanceStd(i, entryCentroids.getValue(),centroidsStd.get(entryCentroids.getKey()));

                if (newZscore < distance) {
                    distance = newZscore;
                    predict_label =  entryCentroids.getKey();
                }


/**   For the mean

                 double newDist = processor.computeDistance(i, entryCentroids.getValue());

                if (newDist < distance) {
                    distance = newDist;
                    predict_label =  entryCentroids.getKey();
                }

 **/

            }

            // Confusion Matrix build
            confusion[(int)predict_label][(int)i.getLabel()]= (confusion[(int)predict_label][(int)i.getLabel()]) + 1;
            total_guess+=1;


            }
        // Confusion Matrix Display
        System.out.println(" ");
        for (int i = 0; i < 10; i++) {
            System.out.print("| ");

            for (int j = 0; j < 10; j++) {

                if(i == j){
                    correct_guesses+=confusion[i][j];
                    System.out.print(   "\033[1m" + " " + confusion[i][j] +"\033[0m" );
                }
                else{
                    System.out.print("  " +confusion[i][j] );
                }
            }
            System.out.println("    |");
        }
        System.out.println(correct_guesses*100/total_guess + "%");
        }
    }
