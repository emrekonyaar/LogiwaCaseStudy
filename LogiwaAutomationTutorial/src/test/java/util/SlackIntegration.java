package util;

import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.webhook.Payload;
import com.github.seratch.jslack.api.webhook.WebhookResponse;

public class SlackIntegration {

    private static String slackWebHook = "YOUR_WEBHOOK_URL";
    private static String channelName = "YOUR_SLACK_CHANNEL_NAME";
    private static String botUserOAuthAccessToken = " OAuth_TOKEN";

    public void sendMessageToSlack(String message) {
        try{
            StringBuilder msgBuilder = new StringBuilder();
            msgBuilder.append(message);

            Payload payload = Payload.builder().channel(channelName).text(msgBuilder.toString()).build();
            WebhookResponse wbResp = Slack.getInstance().send(slackWebHook,payload);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
