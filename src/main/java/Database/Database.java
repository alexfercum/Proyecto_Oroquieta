/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author Alex
 */
public class Database {

    static Firestore db;

    public static void conexion() throws FileNotFoundException, IOException {
        
        FileInputStream serviceAccount = new FileInputStream("oroquietaDatabase.json");
        
        FirebaseOptions options = new FirebaseOptions.Builder().setCredentials(GoogleCredentials.fromStream(serviceAccount)).setDatabaseUrl("https://oroquieta-86e06.firebaseio.com").build();
        
        FirebaseApp.initializeApp(options);
        
        db = FirestoreClient.getFirestore();
        

    }

    public static boolean insertarDatos(String coleccion, String documetno, Map<String, Object> data) {
        try {
            DocumentReference docRef = db.collection(coleccion).document(documetno);
            ApiFuture<WriteResult> result = docRef.set(data);
            System.out.println("Update time :" + result.get().getUpdateTime());

            return true;
        } catch (Exception e) {
            
        }
        return false;
    }

    public static List accederDB(String documento) {
        // asynchronously retrieve all documents
        ApiFuture<QuerySnapshot> future = db.collection(documento).get();
        // future.get() blocks on response
        List<QueryDocumentSnapshot> documents=null;                
        try {
            documents = future.get().getDocuments();
                        
        } catch (InterruptedException | ExecutionException ex) {
            ex.printStackTrace();
            
        }
        return documents; 
    }
    public static Firestore getDatabase(){
        return db;        
    }

}
