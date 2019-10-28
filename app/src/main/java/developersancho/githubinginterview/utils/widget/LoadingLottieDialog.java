package developersancho.githubinginterview.utils.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.StyleRes;

import developersancho.githubinginterview.R;

public class LoadingLottieDialog  extends AlertDialog {
    public static class Builder {
        private Context context;
        private int themeId;
        private boolean cancelable = true;
        private DialogInterface.OnCancelListener cancelListener;

        public Builder setContext(Context context) {
            this.context = context;
            return this;
        }

        public Builder setTheme(@StyleRes int themeId) {
            this.themeId = themeId;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        public Builder setCancelListener(DialogInterface.OnCancelListener cancelListener) {
            this.cancelListener = cancelListener;
            return this;
        }

        public AlertDialog build() {
            return new LoadingLottieDialog(
                    context,
                    themeId != 0 ? themeId : R.style.LoadingDialogDefault,
                    cancelable,
                    cancelListener
            );
        }
    }

    private LoadingLottieDialog(Context context, int theme, boolean cancelable, DialogInterface.OnCancelListener cancelListener) {
        super(context, theme);
        setCancelable(cancelable);
        if (cancelListener != null) setOnCancelListener(cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        setContentView(R.layout.dialog_loading_lottie);
        setCanceledOnTouchOutside(false);
    }
}