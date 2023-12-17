package com.example.whatsnews.presentation.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.whatsnews.R
import androidx.compose.material3.Text
import androidx.compose.ui.tooling.preview.Preview
import com.example.whatsnews.ui.theme.WhatsNewsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier=Modifier,
    text:String,
    readOnly:Boolean,
    onClick:(()->Unit)? = null,
    onValueChange:(String) -> Unit,
    onSearch:()->Unit
){
    val interActionSource = remember {
        //gives all interactions that happens in that text filed
        MutableInteractionSource()
    }
    val isClicked = interActionSource.collectIsPressedAsState().value
    LaunchedEffect(key1 = isClicked){
        if (isClicked){
             onClick?.invoke()
        }
    }

    Box(modifier = modifier){

        TextField(
            value = text,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .searchBarBoarder(),
            readOnly = readOnly,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search ,
                    contentDescription = "search icon",
                    modifier = Modifier
                        .size(20.dp),
                    tint = Color(0xFF4E4B66)
                )
            },

            placeholder = {
                Text(
                    text = stringResource(id = R.string.search),
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFFA0A3BD)
                )
            },

            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch= {
                    onSearch()
                }
            ),

            textStyle = MaterialTheme.typography.bodySmall,
           // interActionSource=interActionSource,
            shape = MaterialTheme.shapes.medium,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFFFFFFF),
                unfocusedContainerColor = Color(0xFFFFFFFF),
                disabledContainerColor = Color(0xFFFFFFFF),
                focusedTextColor = if(isSystemInDarkTheme()) Color.White else Color.Black,
                cursorColor = if(isSystemInDarkTheme()) Color.White else Color.Black,
                disabledIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),


        )
    }
}
fun Modifier.searchBarBoarder():Modifier=composed {
    if(!isSystemInDarkTheme()){
        border(
            width =1.dp,
            color = Color.Black,
            shape = MaterialTheme.shapes.medium
        )
    }
    else {
        this
    }
}
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SearchBarPreview() {
    WhatsNewsTheme {
        SearchBar(text = "", onValueChange = {}, readOnly = false) {

        }
    }
}