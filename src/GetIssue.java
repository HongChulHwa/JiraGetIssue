import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import org.json.JSONObject;


public class GetIssue {


    public static void main(String[] args) throws Exception {

        GetIssue http = new GetIssue();

        http.sendGet("http://jira.twoseed.co.kr:8080/rest/api/2/issue/HW-1");

        }

    private void sendGet(String targetUrl) throws Exception {

        URL url = new URL(targetUrl);

     HttpURLConnection con = (HttpURLConnection) url.openConnection();
     con.setRequestMethod("GET"); //
     con.setRequestProperty("Authorization", "Basic YWRtaW46YWRtaW4"); // Base64로 인코딩 ID:Password
     con.setRequestProperty("Content-Type", "application/json");

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        Charset charset = Charset.forName("UTF-8");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(),charset));


        String inputLine;
        StringBuffer response = new StringBuffer();


                while ((inputLine = in.readLine()) != null) {

            response.append(inputLine);

        }

        in.close();

        JSONObject jsonObject = new JSONObject(response.toString());

        String IssueURL = jsonObject.getString("self");
        String IssueSummary= jsonObject.getJSONObject("fields").getString("summary");
        String IssueCreator= jsonObject.getJSONObject("fields").getJSONObject("creator").getString("name");
        String Issuedescription= jsonObject.getJSONObject("fields").get("description").toString();

        System.out.println("Jira Issue URL : "+IssueURL);
        System.out.println("Issue Creator : "+IssueCreator);
        System.out.println("Issue Summary : "+IssueSummary);
        System.out.println("Issue description : "+Issuedescription);

      System.out.println("조회결과 : " + response.toString());

    }

    }


