package opencvapp;


import org.opencv.core.Core;
import static org.opencv.core.CvType.CV_32F;
import static org.opencv.core.CvType.CV_8U;
import org.opencv.core.Mat;
import org.opencv.ml.ANN_MLP; 
import org.opencv.core.TermCriteria;
import org.opencv.ml.Ml;
/* OpenCV  supports building and training machine learning (ML) algorithms using the org.opencv.ml package 
ANN_MLP stands for “Artificial Neural Network — Multi-Layer Perceptrons” */
public class OpenCVApp {
    
public static void main(String[] args) {
    
   System.load("C:\\Users\\markp\\Downloads\\opencv\\build\\java\\x64\\opencv_java410.dll");
   double[][] XORTrainArray = {
       {0.0,0.0},
       {0.0,1.0},
       {1.0,0.0},
       {1.0,1.0}
   };
   Mat XORTrain = new Mat(4,2,CV_32F);
   XORTrain.put(0,0,XORTrainArray[0]);
   XORTrain.put(1,0,XORTrainArray[1]);
   XORTrain.put(2,0,XORTrainArray[2]);
   XORTrain.put(3,0,XORTrainArray[3]);
   
   System.out.println("Train Inputs : \n" + XORTrain.dump());
   
  double[][] XORTrainOutArray = {
            {0.0},
            {1.0},
            {1.0},
            {0.0}
        };
        Mat XORTrainOut = new Mat(4, 1, CV_32F);
        XORTrainOut.put(0, 0, XORTrainOutArray[0]);
        XORTrainOut.put(1, 0, XORTrainOutArray[1]);
        XORTrainOut.put(2, 0, XORTrainOutArray[2]);
        XORTrainOut.put(3, 0, XORTrainOutArray[3]);
        System.out.println("Train Labels : \n" + XORTrainOut.dump());
  
        ANN_MLP ANN = ANN_MLP.create();
        
        Mat layerSizes = new Mat(3,1,CV_8U);
        layerSizes.put(0, 0, 2);
        layerSizes.put(1, 0, 2);
        layerSizes.put(2, 0, 1);
        ANN.setLayerSizes(layerSizes);
        System.out.println("Layers Sizes : \n" + layerSizes.dump());
        
        ANN.setActivationFunction(ANN_MLP.SIGMOID_SYM);
        ANN.setTrainMethod(ANN_MLP.BACKPROP);

        TermCriteria criteria = new TermCriteria(TermCriteria.EPS + TermCriteria.COUNT, 10000, 0.00000001);
        ANN.setTermCriteria(criteria);
        
        ANN.train(XORTrain, Ml.ROW_SAMPLE, XORTrainOut);
        System.out.println("Model is trained? " + ANN.isTrained());
        
        Mat input_weights = ANN.getWeights(0);
        Mat hidden_weights = ANN.getWeights(1);
        Mat output_weights = ANN.getWeights(2);
        
        System.out.println("Input Layer Weights : \n" + input_weights.dump());
        System.out.println("Hidden Layer Weights : \n" + hidden_weights.dump());
        System.out.println("Output Layer Weights : \n" + output_weights.dump());
        
        try{
             ANN.save("OpenCV_ANN_XOR.yml");
            System.out.println("Model Saved Successfully.");
        } catch(Exception ex) {
            System.err.println("Error Saving Model.");
        }
  }
}