package com.mbah1.jephthah.fieldservicereporter.features.students.presentaion.students

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.graphics.ColorUtils
import com.mbah1.jephthah.fieldservicereporter.R
import com.mbah1.jephthah.fieldservicereporter.features.students.domain.model.StudentModel
import com.mbah1.jephthah.fieldservicereporter.ui.components.ServiceReportFAB
import com.mbah1.jephthah.fieldservicereporter.ui.navigation.components.BottomNavigationBar
import com.mbah1.jephthah.fieldservicereporter.ui.navigation.components.RailNavigationBar
import com.mbah1.jephthah.fieldservicereporter.ui.navigation.components.ServiceReportTopAppBar
import com.mbah1.jephthah.fieldservicereporter.ui.navigation.model.Destination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentsScreen(
    modifier: Modifier = Modifier,
    currentDestination: Destination,
    widthSizeClass: WindowWidthSizeClass,
    navigateUp: () -> Unit,
    onNavigate: (destination: Destination) -> Unit,
    navigateToStudentsDetails:(studentId: String)-> Unit,
    students: List<StudentModel>,
    onDeleteStudent: (student: StudentModel) -> Unit,
    navigateToAddEditStudent:()-> Unit,


    ) {

    Scaffold (

        floatingActionButton = {
            ServiceReportFAB{
                navigateToAddEditStudent()

            }
        }
    ){ contentPadding->

        when(widthSizeClass){
            WindowWidthSizeClass.Compact ->{
                LazyColumn(modifier = Modifier.padding(contentPadding)){
                    if(students.isEmpty()){
                        item {
                            Text(text = "No Student Added")
                        }
                    }else{
                        items(students){ student ->
                            StudentsDisplay(

                                student = student,
                                onDeleteClicked = {
                                    onDeleteStudent(it)
                                },
                                navigateToStudentsDetails = navigateToStudentsDetails
                            )
                        }
                    }
                }
            }

            WindowWidthSizeClass.Medium -> {

                Row(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(top = contentPadding.calculateTopPadding())
                ) {
                    RailNavigationBar(
                        modifier = modifier,
                        currentDestination = currentDestination,
                        onNavigate = onNavigate,
                    )

                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        contentPadding = contentPadding
                    ){
                        items(students){student ->
                            StudentsDisplay(

                                student = student,
                                onDeleteClicked = {
                                    onDeleteStudent(it)
                                },
                                navigateToStudentsDetails = navigateToStudentsDetails
                            )
                        }

                    }
                }



            }

            WindowWidthSizeClass.Expanded -> {
                Row(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(top = contentPadding.calculateTopPadding()),
                ) {
                    RailNavigationBar(
                        modifier = Modifier,
                        currentDestination = currentDestination,
                        onNavigate = onNavigate,
                    )

                    LazyVerticalGrid(
                        columns = GridCells.Fixed(3),
                        modifier = Modifier
                            .padding(horizontal = 32.dp)
                    ){
                        items(students){student ->
                            StudentsDisplay(

                                student = student,
                                onDeleteClicked = {
                                    onDeleteStudent(it)
                                },
                                navigateToStudentsDetails = navigateToStudentsDetails
                            )
                        }

                    }
                }


            }
        }





    }

}



@Composable
fun StudentsDisplay(
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 10.dp,
    cutCornerSize: Dp = 30.dp,
    student: StudentModel,
    navigateToStudentsDetails:(studentId: String)-> Unit,
    onDeleteClicked:(student: StudentModel)-> Unit
) {

    val studentColor = student.studentColor
    Box(
        modifier = modifier
            .padding(16.dp)
    ){
        Canvas(modifier = Modifier.matchParentSize()  ){
            val clipPath = Path().apply {
                lineTo(size.width - cutCornerSize.toPx(), 0f)
                lineTo(size.width, cutCornerSize.toPx())
                lineTo(size.width, size.height)
                lineTo(0f, size.height)
                close()
            }
            clipPath(clipPath){
                drawRoundRect(
                    color = Color(studentColor),
                    size = size,
                    cornerRadius = CornerRadius(cornerRadius.toPx())
                )

                drawRoundRect(
                    color = Color(
                        ColorUtils.blendARGB(studentColor, 0x000000, 0.2f)
                    ),
                    topLeft = Offset(size.width - cutCornerSize.toPx(), 0f),
                    size = Size(cutCornerSize.toPx(), cutCornerSize.toPx()),
                    cornerRadius = CornerRadius(cornerRadius.toPx())
                )
            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
            ) {
                Surface(
                    modifier = Modifier
                        .size(80.dp),
                    shape = RoundedCornerShape(50),
                    color = MaterialTheme.colorScheme.surface

                ) {
                    Image(
                        painter = painterResource(id = R.drawable.student_photo_placeholder),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.width(30.dp))

                Column {
                    Text(
                        text = student.studentName,
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.Black,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis

                    )
                    Spacer(modifier = Modifier.height(8.dp))


                    Row {
                        Text(
                            text = "Phone:",
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.Black,
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = student.phoneNumber,
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.Black,

                            )

                    }


                }
            }
            Divider(modifier= Modifier.padding(top = 10.dp, bottom = 6.dp))
            Row(
                modifier= Modifier,

                ) {
                Button(
                    onClick = {
                        onDeleteClicked.invoke(student)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red.copy(0.2f))
                ) {
                    Text(text = "Delete")
                }

                Spacer(modifier = Modifier.width(50.dp))

                Button(
                    onClick = {
                        navigateToStudentsDetails.invoke(student.id.toString())
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                ) {
                    Text(text = "Details")
                }

            }

        }




    }


}