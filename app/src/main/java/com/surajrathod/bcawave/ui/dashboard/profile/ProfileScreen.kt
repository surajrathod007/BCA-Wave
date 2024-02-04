package com.surajrathod.bcawave.ui.dashboard.profile

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.ButtonColors
import androidx.compose.material3.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.toFontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.surajrathod.bcawave.R
import com.surajrathod.bcawave.ui.theme.PrimaryBgColor
import com.surajrathod.bcawave.ui.theme.PrimaryColor
import com.surajrathod.bcawave.ui.theme.UnitColor

@Composable
fun ProfileScreen(paddingValues: PaddingValues = PaddingValues(0.dp)) {

    val mContext = LocalContext.current

    Surface(
        color = PrimaryBgColor, modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
    ) {
        Column {
            Column(
                Modifier
                    .background(PrimaryColor)
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
            ) {
                Text(
                    text = "Share Our App",
                    color = Color.White,
                    modifier = Modifier.padding(start = 6.dp),
                    fontFamily = Font(
                        R.font.main_bold
                    ).toFontFamily(),
                    fontSize = 18.sp
                )
            }
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                text = "Share Our App With Your Friends!",
                fontFamily = Font(
                    R.font.main_heavy
                ).toFontFamily(),
                color = PrimaryColor,
                fontSize = 28.sp,
                textAlign = TextAlign.Center
            )
            Button(
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 12.dp)
                    .fillMaxWidth(),
                onClick = {
                    shareApp(mContext)
                },
                colors = ButtonDefaults.buttonColors(containerColor = UnitColor)
            ) {
                Text(
                    text = "Click Here To Share", fontFamily = Font(
                        R.font.main_semibold
                    ).toFontFamily(), fontSize = 14.sp, color = Color.White
                )
            }
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Change Log :",
                fontFamily = Font(
                    R.font.main_bold
                ).toFontFamily(),
                fontSize = 24.sp,
                color = PrimaryColor,
                textAlign = TextAlign.Center
            )
            Box(
                modifier = Modifier
                    .padding(12.dp)
                    .background(
                        Color.White, CircleShape.copy(
                            CornerSize(6.dp)
                        )
                    )
            ) {
                Text(
                    modifier = Modifier.padding(6.dp),
                    text = "If you find any error in code, then please report that program !",
                    fontFamily = Font(R.font.main_semibold).toFontFamily(), color = PrimaryColor
                )
            }
            Box(
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .background(
                        Color.White, CircleShape.copy(
                            CornerSize(6.dp)
                        )
                    )
            ) {
                Text(
                    modifier = Modifier
                        .padding(6.dp)
                        .fillMaxWidth(),
                    text = "------V 1.7---------\n-Ui improved\n-New programs Added\n------------\nTo contact us on\nLinkedin : @surajrathod007\nLinkedin : @manshalkhatri",
                    fontFamily = Font(R.font.main_semibold).toFontFamily(), color = PrimaryColor
                )
            }
            Text(
                text = "app version : 1.7",
                color = PrimaryColor,
                modifier = Modifier
                    .padding(top = 12.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontFamily = Font(R.font.main_regular).toFontFamily()
            )
        }
    }
}


fun shareApp(context: Context) {
    val intent = Intent()
    intent.action = Intent.ACTION_SEND
    intent.type = "text/plain"
    intent.putExtra(
        Intent.EXTRA_TEXT,
        "Hey i found an app where you can get all BCA Practical programs for free!! \n Download Now : https://play.google.com/store/apps/details?id=com.surajrathod.bcaprogram"
    )
    context.startActivity(Intent.createChooser(intent, "Share With Friends"))
}

@Preview(showSystemUi = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}