package com.example.listycity3

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.listycity3.ui.theme.ListyCity3Theme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.FloatingActionButton
import androidx.compose.foundation.layout.fillMaxSize

@Composable
fun CityListScreen(
    cities: List<City>,
    //addCity implementation
    onAddCity: (City) -> Unit,
    onupdateCity:(oldCity: City, updatedCity: City) -> Unit,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit?) -> Unit
) {
    //set two new vars to store typed cities and provinces
    var newCityName by remember { mutableStateOf("") }
    var newProvinceName by remember { mutableStateOf("") }
    var showAddCityFields by remember { mutableStateOf(false) }

    //set selected city
    //default is null
    var selected by remember { mutableStateOf(false) }
    var selectedCity by remember { mutableStateOf<City?>(null) }


    Column(modifier = modifier.fillMaxSize()) {

        //new row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ){
            FloatingActionButton(
                modifier = Modifier.padding(16.dp),
                onClick = {
                    showAddCityFields = !showAddCityFields
                }
            ){
                Text("+")
            }
         // new row
        }

        //two actions: one is to add city. another one is to update city
        if(showAddCityFields || selected) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = newCityName,
                    onValueChange = { newCityName = it },
                    //label itself showed in a text box to indicate what should be the input
                    label = { Text(if(selected)"Update City" else "City") },
                    modifier = Modifier.weight(1f).padding(top = 30.dp)
                )

                Spacer(modifier = Modifier.width(8.dp))


                OutlinedTextField(
                    value = newProvinceName,
                    onValueChange = { newProvinceName = it },
                    //label itself showed in a text box to indicate what should be the input
                    label = { Text(if(selected)"Update Province" else "Province") },
                    modifier = Modifier.weight(1f).padding(top = 30.dp)
                )

                //adding a button for addCity fun

                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    modifier = Modifier.padding(vertical = 12.dp).padding(top = 30.dp),
                    onClick = {
                        if (newCityName.isNotBlank() && newProvinceName.isNotBlank()) {
                            if(selected && selectedCity != null) {
                                //set new val for replacing the old city
                                val updatedCity = City(
                                    name = newCityName,
                                    province = newProvinceName
                                )
                                onupdateCity(selectedCity!!, updatedCity )
                            } else {
                                onAddCity(
                                    City(
                                        name = newCityName,
                                        province = newProvinceName
                                    )
                                )
                            //else
                            }
                            newCityName = ""
                            newProvinceName = ""
                            showAddCityFields = false
                            selected = !selected
                            selectedCity = null
                        }
                    }
                ) {
                    Text(if(selected) "UPDATE CITY" else "ADD CITY")
                }


                //row
            }
        // if showaddcityfields
        }

        LazyColumn(modifier = modifier.fillMaxSize()) {
            itemsIndexed(cities) { index, city ->
                CityRow(
                    city = city,
                    onClick = {
                            selectedCity = city
                            selected = !selected
                    }
                    )
                if (index < cities.lastIndex) {
                    HorizontalDivider()
                }
            }
        }
    }


//column
}

@Composable
fun CityRow(
    city: City,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        Text(
            text = city.name,
            fontSize = 30.sp,
            modifier = Modifier
                .weight(1f)
                //making this object to be clickable
                .clickable {
                    onClick()
                }
        )

        Text(
            text = city.province,
            fontSize = 30.sp,
            modifier = Modifier
                .weight(1f)
                //making this object to be clickable
                .clickable {
                    onClick()
                }
        )
    }
}




@Composable
@Preview(showBackground = true)
fun CityListScreenPreview() {
    ListyCity3Theme {
        CityListScreen(
            cities = listOf(
                City("Edmonton", "AB"),
                City("Vancouver", "BC"),
                City("Calgary", "AB")
            ),
            onAddCity = {},
            onClick = {},
            onupdateCity = { _,_ -> }
        )
    }
}

